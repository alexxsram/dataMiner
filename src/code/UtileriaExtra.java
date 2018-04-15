package code;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBoxAndWhiskerRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
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
    ///FAVOR DE NO MOVER NADA, ESTO HACE FUNCIONAL LA PARTE DEL ANÁLISIS UNIVARIABLE Y BIVARIABLE
    public UtileriaExtra() {
        ///Void constructor
    }
    
    //funciones extras que me servirán de algo :v
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
    
    //funciones para pasar datos de listas
    public ArrayList<String> getListaNominal(ArrayList<ArrayList<String>> instanceList, ArrayList<Attribute> attributeList, int indice) {
        ArrayList<String> nominalList = new ArrayList<String>();
        for(int i = 0; i < instanceList.size(); i++) {
            if(Pattern.matches(attributeList.get(indice).getExpresionRegular(), instanceList.get(i).get(indice))) {
                nominalList.add(instanceList.get(i).get(indice));
            }
        }
        Collections.sort(nominalList);
        return nominalList;
    }
    
    public ArrayList<Double> getListaNumerica(ArrayList<ArrayList<String>> instanceList, ArrayList<Attribute> attributeList, int indice) {
       ArrayList<Double> numericList = new ArrayList<Double>();
        for(int i = 0; i < instanceList.size(); i++) {
            if(Pattern.matches(attributeList.get(indice).getExpresionRegular(), instanceList.get(i).get(indice))) {
                numericList.add(Double.parseDouble(instanceList.get(i).get(indice)));
            }
        }
        return numericList;
    }

    ///para el analisis univariable de númericos
    public double[] llenarArreglo(ArrayList<Double> listaNumericos) {
        double[] vectorNumericos = new double[listaNumericos.size()];
        for(int i = 0; i < vectorNumericos.length; i++) {
            vectorNumericos[i] = listaNumericos.get(i);
        }
        return vectorNumericos;
    }
    
    public double[] metodoBurbuja(double[] vectorNumericos, int ordenacion) {
        double aux = 0, cantidadVector = vectorNumericos.length;
        for(int i = 0; i < cantidadVector - 1; i++) {
            for(int j = i + 1; j < cantidadVector; j++) {
                if(ordenacion == 0) {
                    if(vectorNumericos[i] > vectorNumericos[j]) {
                        aux = vectorNumericos[j];
                        vectorNumericos[j] = vectorNumericos[i];
                        vectorNumericos[i] = aux;
                    }
                }
                else if(ordenacion == 1) {
                    if(vectorNumericos[i] < vectorNumericos[j]) {
                        aux = vectorNumericos[i];
                        vectorNumericos[i] = vectorNumericos[j];
                        vectorNumericos[j] = aux;
                    }
                }
            }
        }
        return vectorNumericos;
    }
    
    public double calcularMedia(double[] vectorNumericos) {
        double sumatoria = 0, media = 0;
        for(int i = 0; i < vectorNumericos.length; i++) {
            sumatoria += vectorNumericos[i];
        }
        media = (sumatoria / (double)vectorNumericos.length);
        return media;
    }
    
    public double calcularMediana(double[] vectorNumericos) {
        int posicion = 0, cantidadVector = vectorNumericos.length;
        double aux1 = 0, mediana = 0;
        
        vectorNumericos = metodoBurbuja(vectorNumericos, 0); //ordeno de menor a mayor
        
        aux1 = cantidadVector / 2;
        if(cantidadVector%2 == 0) {
            posicion = (int)aux1;
            mediana = (double)((vectorNumericos[posicion - 1] + vectorNumericos[posicion])/ 2.0);
        }
        if(cantidadVector%2 == 1) {
            posicion = (int)(aux1 + 0.5);
            mediana = (double)(vectorNumericos[posicion]);
        }
        return mediana;
    }
    
    public double calcularModa(double[] vectorNumericos) {
        double moda = 0;
        int cantidadVector = vectorNumericos.length;
        int frecuencia, frecuenciaModa = 0;
        
        vectorNumericos = metodoBurbuja(vectorNumericos, 0); //ordeno de menor a mayor
        
        for(int i = 0; i < cantidadVector; i++) {
            frecuencia = 1;
            for(int j = i + 1; j < cantidadVector; j++) {
                if(vectorNumericos[i] == vectorNumericos[j]) {
                    frecuencia++;
                }
            }
            if(frecuencia > frecuenciaModa) {
                frecuenciaModa = frecuencia;
                moda = vectorNumericos[i];
            }
        }
        return moda;
    }
    
    public double calcularDesviacionEstandar(double[] vectorNumericos) {
        double media, sumatoria = 0, cantidadVector = vectorNumericos.length, desviacionEstandar = 0;
        
        media = calcularMedia(vectorNumericos);
        
        for(int i = 0; i < cantidadVector; i++) {
            sumatoria += Math.pow((vectorNumericos[i] - media), 2);
        }
        desviacionEstandar = Math.sqrt(sumatoria / (double)cantidadVector);
        return desviacionEstandar;
    }
    
    //grafica de box plot para numéricos, las 4 funciones sirven para crear el gráfico
    public ArrayList<Double> crearListaValores(ArrayList<Double> numericList) {
        ArrayList<Double> list = new ArrayList<Double>();
        for(int i = 0; i < numericList.size(); i++) {
            list.add(new Double(numericList.get(i)));
        }
        return list;
    }

    public BoxAndWhiskerXYDataset crearDataset(ArrayList<Double> numericList) {
        DefaultBoxAndWhiskerXYDataset dataset = new DefaultBoxAndWhiskerXYDataset("Gráfico");
        RegularTimePeriod time = new Day();
        ArrayList<Double> list = crearListaValores(numericList);
        dataset.add(time.getStart(), BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(list));       
        return dataset;
    }

    private JFreeChart crearChart(String attributeName, BoxAndWhiskerXYDataset dataset) {
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

    public ChartFrame crearBoxPlot(String title, String attributeName, ArrayList<Double> numericList) {
        JFreeChart chart = crearChart(attributeName, crearDataset(numericList));
        ChartFrame frame = new ChartFrame(title, chart);
        return frame;
    }
    
    //gráfica de frecuencias para categóricos
    public ChartFrame getGraficaPie(String title, String attributeName, ArrayList<String> nominalList) {
        ArrayList<String> auxNominalList = new ArrayList<String>();
        ArrayList<Integer> auxPercNominalList = new ArrayList<Integer>();
        
        String cadena = "";
        for(int i = 0; i < nominalList.size(); i++) {
            if(cadena.equals(nominalList.get(i))) {
                int frecuencia = auxPercNominalList.get(auxPercNominalList.size() - 1);
                auxPercNominalList.remove(auxPercNominalList.size() - 1);
                frecuencia++;
                auxPercNominalList.add(frecuencia);
            }
            else {
                auxNominalList.add(nominalList.get(i));
                auxPercNominalList.add(1);
            }
            cadena = nominalList.get(i);
        }
        
        int[] porcentaje = new int[auxPercNominalList.size()];
        for(int j = 0; j < auxPercNominalList.size(); j++) {
            porcentaje[j] = (auxPercNominalList.get(j) * 100) / nominalList.size();
        }
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        for(int k = 0; k < auxPercNominalList.size(); k++) {
            dataset.setValue(auxNominalList.get(k), porcentaje[k]);
        }
        
        JFreeChart chart = ChartFactory.createPieChart("Gráfica de " + attributeName, dataset, true, true, false);
        
        ChartFrame frame = new ChartFrame(title, chart);
        
        return frame;
    }
   
    public ChartFrame getGraficaBarras(String title, String attributeName, ArrayList<String> nominalList) {
        ArrayList<String> auxNominalList = new ArrayList<String>();
        ArrayList<Integer> auxPercNominalList = new ArrayList<Integer>();
        
        String cadena = "";
        for(int i = 0; i < nominalList.size(); i++) {
            if(cadena.equals(nominalList.get(i))) {
                int frecuencia = auxPercNominalList.get(auxPercNominalList.size() - 1);
                auxPercNominalList.remove(auxPercNominalList.size() - 1);
                frecuencia++;
                auxPercNominalList.add(frecuencia);
            }
            else {
                auxNominalList.add(nominalList.get(i));
                auxPercNominalList.add(1);
            }
            cadena = nominalList.get(i);
        }
        
        int[] porcentaje = new int[auxPercNominalList.size()];
        for(int j = 0; j < auxPercNominalList.size(); j++) {
            porcentaje[j] = auxPercNominalList.get(j);
        }
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int k = 0; k < auxPercNominalList.size(); k++) {
            dataset.setValue(porcentaje[k], auxNominalList.get(k), auxNominalList.get(k));
        }
        
        JFreeChart chart = ChartFactory.createBarChart("Gráfica de " + attributeName, "Valores", "Frecuencia", dataset, PlotOrientation.HORIZONTAL, true, true, true);
        
        ChartFrame frame = new ChartFrame(title, chart);
        
        return frame;
    } 
    
    //para el análisis bivariable de numéricos
    public double calcularCoeficientePearson(ArrayList<Double> listaA, ArrayList<Double> listaB) {
        double[] vectorA = llenarArreglo(listaA);
        double mediaA = redondeoDecimales(calcularMedia(vectorA), 2);
        double desvestA = calcularDesviacionEstandar(vectorA);
        
        double[] vectorB = llenarArreglo(listaB);
        double mediaB = redondeoDecimales(calcularMedia(vectorB), 2);
        double desvestB = calcularDesviacionEstandar(vectorB);
        
        double sumatoria = 0;
        for(int i = 0; i < vectorA.length; i++) {
            sumatoria += ((vectorA[i] - mediaA) * (vectorB[i] - mediaB));
        }
        double coeficientePearson = (sumatoria / (vectorA.length * desvestA * desvestB));
        
        return coeficientePearson;
    }
    
    public XYDataset cargarDataSet(ArrayList<Double> listaA, ArrayList<Double> listaB) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries serie = new XYSeries("Distribución");
        for(int i = 0; i < listaA.size(); i++) {
            serie.add(listaA.get(i), listaB.get(i));
        }
        dataset.addSeries(serie);
        return dataset;
    }
    
    public ChartFrame getGraficaDispersion(String attributeA, String attributeB, ArrayList<Double> listaA, ArrayList<Double> listaB) {
        XYDataset dataset = cargarDataSet(listaA, listaB);
        
        JFreeChart chart = ChartFactory.createScatterPlot("Dispersión entre " + attributeA + " vs " + attributeB, "A->" + attributeA, "B->" + attributeB, dataset);
        
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(255, 228, 196));
        
        ChartFrame frame = new ChartFrame("Análisis bivariable", chart);
        
        return frame;
    }
}
