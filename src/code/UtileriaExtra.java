package code;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.regex.Pattern;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.xy.XYBoxAndWhiskerRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.BoxAndWhiskerCalculator;
import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerXYDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author ALEJO SALGADO
 */
public class UtileriaExtra {
    public UtileriaExtra() {
        
    }
    
    //funciones extras que me servirán de algo
    public void limpiarTabla(JTable tbl) {
        DefaultTableModel r = (DefaultTableModel)tbl.getModel();
        while(r.getRowCount() > 0) {
            r.removeRow(0);
        }
        TableColumnModel c = tbl.getColumnModel();
        while(c.getColumnCount() > 0) {
            c.removeColumn(c.getColumn(0));
        }
    }
    
    public double redondeoDecimales(double numero, int numeroDecimales) {
        BigDecimal redondeado = new BigDecimal(numero).setScale(numeroDecimales, RoundingMode.HALF_EVEN);
        return redondeado.doubleValue();
    }
    
    public ArrayList<String> valoresUnicos(ArrayList<String> nominalList) {
        ArrayList<String> listaUnica = new ArrayList<String>();
        HashSet<String> map = new HashSet<String>(nominalList);
        for(String aux: map) {
            listaUnica.add(aux);
        }
        return listaUnica;
    }
    
    public ArrayList<Double> frecuenciaValoresUnicos(ArrayList<String> nominalList) {
        ArrayList<Double> listaUnica = new ArrayList<Double>();
        HashSet<String> map = new HashSet<String>(nominalList);
        for(String aux: map) {            
            listaUnica.add(new Double(Collections.frequency(nominalList, aux)));
        }
        return listaUnica;
    }
    
    //para el análisis univariable de numéricos
    public ArrayList<String> getListaNominalUni(ArrayList<ArrayList<String>> instanceList, ArrayList<Attribute> attributeList, int index) {
        ArrayList<String> nominalList = new ArrayList<String>();
        for(int i = 0; i < instanceList.size(); i++) {
            if(Pattern.matches(attributeList.get(index).getExpresionRegular(), instanceList.get(i).get(index))) {
                nominalList.add(instanceList.get(i).get(index));
            }
        }
        return nominalList;
    }
    
    public ArrayList<Double> getListaNumericaUni(ArrayList<ArrayList<String>> instanceList, ArrayList<Attribute> attributeList, int index) {
        ArrayList<Double> numericList = new ArrayList<Double>();
        for(int i = 0; i < instanceList.size(); i++) {
            if(Pattern.matches(attributeList.get(index).getExpresionRegular(), instanceList.get(i).get(index))) {
                numericList.add(Double.parseDouble(instanceList.get(i).get(index)));
            }
        }
        return numericList;
    }
    
    public double[] llenarDoubleArray(ArrayList<Double> numericList) {
        double[] numericVector = new double[numericList.size()];
        for(int i = 0; i < numericVector.length; i++) {
            numericVector[i] = numericList.get(i);
        }
        return numericVector;
    }
    
    public double[] metodoBurbuja(double[] numericVector, int sortMethod) {
        double aux = 0, vectorSize = numericVector.length;
        for(int i = 0; i < vectorSize - 1; i++) {
            for(int j = i + 1; j < vectorSize; j++) {
                if(sortMethod == 0) { //ordena de menor a mayor
                    if(numericVector[i] > numericVector[j]) {
                        aux = numericVector[j];
                        numericVector[j] = numericVector[i];
                        numericVector[i] = aux;
                    }
                }
                else if(sortMethod == 1) { //ordena de mayor a menor
                    if(numericVector[i] < numericVector[j]) {
                        aux = numericVector[i];
                        numericVector[i] = numericVector[j];
                        numericVector[j] = aux;
                    }
                }
            }
        }
        return numericVector;
    }
    
    public double calcularMedia(double[] numericVector) {
        double sumatoria = 0, media = 0;
        for(int i = 0; i < numericVector.length; i++) {
            sumatoria += numericVector[i];
        }
        media = (sumatoria / (double)numericVector.length);
        return media;
    }
    
    public double calcularMediana(double[] numericVector) {
        int posicion = 0, vectorSize = numericVector.length;
        double aux1 = 0, mediana = 0;
        
        numericVector = metodoBurbuja(numericVector, 0); //ordeno de menor a mayor
        
        aux1 = vectorSize / 2;
        if(vectorSize%2 == 0) {
            posicion = (int)aux1;
            mediana = (double)((numericVector[posicion - 1] + numericVector[posicion])/ 2.0);
        }
        if(vectorSize%2 == 1) { 
            posicion = (int)(aux1 + 0.5);
            mediana = (double)(numericVector[posicion]);
        }
        return mediana;
    }
    
    public double calcularModa(double[] numericVector) {
        int vectorSize = numericVector.length;
        int frecuencia, frecuenciaModa = 0;
        double moda = 0;
        
        numericVector = metodoBurbuja(numericVector, 0); //ordeno de menor a mayor
        
        for(int i = 0; i < vectorSize; i++) {
            frecuencia = 1;
            for(int j = i + 1; j < vectorSize; j++) {
                if(numericVector[i] == numericVector[j]) {
                    frecuencia++;
                }
            }
            if(frecuencia > frecuenciaModa) {
                frecuenciaModa = frecuencia;
                moda = numericVector[i];
            }
        }
        return moda;
    }
    
    public double calcularDesviacionEstandar(double[] numericVector) {
        double media, sumatoria = 0, vectorSize = numericVector.length, desviacionEstandar = 0;
        
        media = calcularMedia(numericVector);
        
        for(int i = 0; i < vectorSize; i++) {
            sumatoria += Math.pow((numericVector[i] - media), 2);
        }
        desviacionEstandar = Math.sqrt(sumatoria / (double)vectorSize);
        return desviacionEstandar;
    }
    
    //gráfica de box plot para numéricos
    public ArrayList<Double> crearListaValores(ArrayList<Double> numericList) {
        ArrayList<Double> list = new ArrayList<Double>();
        for(int i = 0; i < numericList.size(); i++) {
            list.add(new Double(numericList.get(i)));
        }
        return list;
    }

    public BoxAndWhiskerXYDataset cargarDatasetBoxPlot(ArrayList<Double> numericList) {
        DefaultBoxAndWhiskerXYDataset dataset = new DefaultBoxAndWhiskerXYDataset("Gráfico");
        RegularTimePeriod time = new Day();
        ArrayList<Double> list = crearListaValores(numericList);
        dataset.add(time.getStart(), BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(list));       
        return dataset;
    }

    private JFreeChart crearChartBoxPlot(String attributeName, BoxAndWhiskerXYDataset dataset) {
        DateAxis domainAxis = new DateAxis("x");
        NumberAxis rangeAxis = new NumberAxis("y");
        XYItemRenderer renderer = new XYBoxAndWhiskerRenderer();
        XYPlot plot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);
        JFreeChart chart = new JFreeChart("Box plot de " + attributeName, plot);
        chart.setBackgroundPaint(Color.white);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartUtilities.applyCurrentTheme(chart);
        return chart;
    }

    public ChartFrame getBoxPlot(String title, String attributeName, ArrayList<Double> numericList) {
        JFreeChart chart = crearChartBoxPlot(attributeName, cargarDatasetBoxPlot(numericList));
        ChartFrame frame = new ChartFrame(title, chart);
        return frame;
    }
    
    //gráfica de frecuencias para categóricos
    public ChartFrame getGraficaPie(String title, String attributeName, ArrayList<String> nominalList) {
        ArrayList<String> auxNominalList = valoresUnicos(nominalList);
        ArrayList<Double> auxFrecNominalList = frecuenciaValoresUnicos(nominalList);
        DefaultPieDataset dataset = new DefaultPieDataset();
        for(int i = 0; i < auxFrecNominalList.size(); i++) {
            dataset.setValue(auxNominalList.get(i), auxFrecNominalList.get(i));
        }
        JFreeChart chart = ChartFactory.createPieChart("Gráfica de " + attributeName, dataset, true, true, false);
        ChartFrame frame = new ChartFrame(title, chart);
        return frame;
    }
   
    public ChartFrame getGraficaBarras(String title, String attributeName, ArrayList<String> nominalList) {
        ArrayList<String> auxNominalList = valoresUnicos(nominalList);
        ArrayList<Double> auxFrecNominalList = frecuenciaValoresUnicos(nominalList);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i = 0; i < auxFrecNominalList.size(); i++) {
            dataset.setValue(auxFrecNominalList.get(i), auxNominalList.get(i), auxNominalList.get(i));
        }
        JFreeChart chart = ChartFactory.createBarChart("Gráfica de " + attributeName, "Valores", "Frecuencia", dataset, PlotOrientation.HORIZONTAL, true, true, true);
        ChartFrame frame = new ChartFrame(title, chart);
        return frame;
    } 
    
    //para el análisis bivariable de numéricos
    public double calcularCoeficientePearson(ArrayList<Double> listA, ArrayList<Double> listB) {
        double[] vectorA = llenarDoubleArray(listA);
        double mediaA = redondeoDecimales(calcularMedia(vectorA), 2);
        double desvestA = calcularDesviacionEstandar(vectorA);
        
        double[] vectorB = llenarDoubleArray(listB);
        double mediaB = redondeoDecimales(calcularMedia(vectorB), 2);
        double desvestB = calcularDesviacionEstandar(vectorB);
        
        double sumatoria = 0;
        for(int i = 0; i < vectorA.length; i++) {
            sumatoria += ((vectorA[i] - mediaA) * (vectorB[i] - mediaB));
        }
        double coeficientePearson = sumatoria / (vectorA.length * desvestA * desvestB);
        
        return coeficientePearson;
    }
    
    public XYDataset cargarDatasetDispersion(ArrayList<Double> listA, ArrayList<Double> listB) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries serie = new XYSeries("Distribución");
        for(int i = 0; i < listA.size(); i++) {
            serie.add(listA.get(i), listB.get(i));
        }
        dataset.addSeries(serie);
        return dataset;
    }
    
    public ChartFrame getGraficaDispersion(String attributeA, String attributeB, ArrayList<Double> listA, ArrayList<Double> listB) {
        XYDataset dataset = cargarDatasetDispersion(listA, listB);
        JFreeChart chart = ChartFactory.createScatterPlot("Dispersión entre " + attributeA + " vs " + attributeB, "A -> " + attributeA, "B -> " + attributeB, dataset);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(0, 255, 150));
        ChartFrame frame = new ChartFrame("Análisis bivariable", chart);
        return frame;
    }
    
    //para el análisis univariable de categóricos
    public ArrayList<ArrayList<String>> getListaNominalBiv(ArrayList<ArrayList<String>> instanceList, ArrayList<Attribute> attributeList, int index1, int index2) {
        ArrayList<String> auxList;
        ArrayList<ArrayList<String>> nominalList = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < instanceList.size(); i++) {
            boolean attribute1 = Pattern.matches(attributeList.get(index1).getExpresionRegular(), instanceList.get(i).get(index1));
            boolean attribute2 = Pattern.matches(attributeList.get(index2).getExpresionRegular(), instanceList.get(i).get(index2));
            if((attribute1 == true) && (attribute2 == true)) {
                auxList = new ArrayList<String>();
                auxList.add(instanceList.get(i).get(index1));
                auxList.add(instanceList.get(i).get(index2));
                nominalList.add(auxList);
            }
        }
        return nominalList;
    }
    
    public ArrayList<String> getListaInstanciaAtributo(ArrayList<ArrayList<String>> newInstanceList, int index) {
        ArrayList<String> instanceAttributeList = new ArrayList<String>();
        for(int i = 0; i < newInstanceList.size(); i++) {
            instanceAttributeList.add(newInstanceList.get(i).get(index));
        }
        return instanceAttributeList;
    }
           
    public int[][] getMatrizFrecuencias(ArrayList<String> instanceAttributeListA, ArrayList<String> instanceAttributeListB, ArrayList<String> valuesOfA, ArrayList<String> valuesOfB) {
        int rows = valuesOfA.size(), columns = valuesOfB.size();
        int c, r;
        int[][] frequenceMatrix = new int[rows][columns];
        
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                frequenceMatrix[i][j] = 0;
            }
        }
        
        for(int i = 0; i < instanceAttributeListA.size(); i++) {
            if(!instanceAttributeListA.get(i).equals("") && !instanceAttributeListB.get(i).equals("")) {
                r = valuesOfA.indexOf(instanceAttributeListA.get(i));
                c = valuesOfB.indexOf(instanceAttributeListB.get(i));
                frequenceMatrix[r][c]++;
            }
        }
        
        return frequenceMatrix;
    }
    
    public double calcularChiCuadrada(int[][] frequenceMatrix, int instanceSize) {
        
        int rows = frequenceMatrix.length, columns = frequenceMatrix[0].length; 
                
        int sumaRows, sumaColumns;
        int[] rowsVector = new int[rows];
        int[] columnsVector = new int[columns];
        
        for(int i = 0; i < rows; i++) {
            sumaRows = 0;
            for(int j = 0; j < columns; j++) {
                sumaRows += frequenceMatrix[i][j];
            }
            rowsVector[i] = sumaRows;
        }
        
        for(int i = 0; i < columns; i++) {
            sumaColumns = 0;
            for(int j = 0; j < rows; j++) {
                sumaColumns += frequenceMatrix[j][i];
            }
            columnsVector[i] = sumaColumns;
        }
        
        double chiCuadrada = 0, expectedFrequence;
        double[][] expectedFrequenceMatrix = new double[rows][columns];
        for(int i = 0; i < rowsVector.length; i++) {
            for(int j = 0; j < columnsVector.length; j++) {
                expectedFrequence = (rowsVector[i] * columnsVector[j]) / (double)instanceSize;
                expectedFrequenceMatrix[i][j] = expectedFrequence;
                chiCuadrada += Math.pow((frequenceMatrix[i][j] - expectedFrequenceMatrix[i][j]), 2) / expectedFrequenceMatrix[i][j];
            }
        }
        return chiCuadrada;
    }
    
    public double calcularCoeficienteTschuprow(double chiCuadrada, int instanceAttribteListSize, int rowSize, int columnSize) {
        double divisor = instanceAttribteListSize * Math.sqrt((columnSize - 1) * (rowSize - 1));
        double coeficienteTschuprow = Math.sqrt(chiCuadrada / divisor);
        return coeficienteTschuprow;
    }
    
    public CategoryDataset cargarDatasetStacked(int[][] frequenceMatrix, ArrayList<String> valuesOfA, ArrayList<String> valuesOfB) {
        String[] vectorA = new String[valuesOfA.size()];
        String[] vectorB = new String[valuesOfB.size()];
        
        for(int i = 0; i < vectorA.length; i++) {
            vectorA[i] = valuesOfA.get(i);
        }
        for(int j = 0; j < vectorB.length; j++) {
            vectorB[j] = valuesOfB.get(j);
        }
        
        double[][] data = new double[vectorA.length][vectorB.length];
        for(int i = 0; i < vectorA.length; i++) {
            for(int j = 0; j < vectorB.length; j++) {
                data[i][j] = new Double(frequenceMatrix[i][j]);
            }
        }
        
        return DatasetUtilities.createCategoryDataset(vectorA, vectorB, data);
    }
    
    public JFreeChart getGraficaApilada(String attributeA, String attributeB, int[][] frequenceMatrix, ArrayList<String> valuesOfA, ArrayList<String> valuesOfB) {
        CategoryDataset dataset = cargarDatasetStacked(frequenceMatrix, valuesOfA, valuesOfB);
        JFreeChart chart = ChartFactory.createStackedBarChart("Categorización entre " + attributeA + " vs " + attributeB, "Category", "Values", dataset, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
        StackedBarRenderer renderer = (StackedBarRenderer)plot.getRenderer();
        renderer.setItemLabelsVisible(true);
        return chart;
    }
}
