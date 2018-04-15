package code;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

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

/**
 *
 * @author ALEJO SALGADO
 */
public class UtileriaExtra {
    ///FAVOR DE NO MOVER NADA, ESTO HACE FUNCIONAL LA PARTE DEL ANÁLISIS UNIVARIABLE Y BIVARIABLE
    public UtileriaExtra() {
    }
    
    //funciones extras que me servirán de algo :v
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
    
    public ArrayList<Integer> getListaNumerica(ArrayList<ArrayList<String>> instanceList, ArrayList<Attribute> attributeList, int indice) {
       ArrayList<Integer> numericList = new ArrayList<Integer>();
        for(int i = 0; i < instanceList.size(); i++) {
            if(Pattern.matches(attributeList.get(indice).getExpresionRegular(), instanceList.get(i).get(indice))) {
                numericList.add(Integer.parseInt(instanceList.get(i).get(indice)));
            }
        }
        Collections.sort(numericList);
        return numericList;
    }

    ///para el analisis univariable de númericos
    public int[] llenarArreglo(ArrayList<Integer> listaNumericos) {
        Collections.sort(listaNumericos);
        int[] vectorNumericos = new int[listaNumericos.size()];
        for(int i = 0; i < vectorNumericos.length; i++) {
            vectorNumericos[i] = listaNumericos.get(i);
        }
        return vectorNumericos;
    }
    
    public double calcularMedia(int[] vectorNumericos) {
        double sumatoria = 0, media = 0;
        for(int i = 0; i < vectorNumericos.length; i++) {
            sumatoria += vectorNumericos[i];
        }
        media = (sumatoria / (double)vectorNumericos.length);
        return media;
    }
    
    public double calcularMediana(int[] vectorNumericos) {
        int posicion = 0, cantidadVector = vectorNumericos.length;
        double aux1 = 0, mediana = 0;
        aux1 = cantidadVector / 2;
        if(cantidadVector%2 == 0) {
            posicion = (int)aux1;
            mediana = (double)((vectorNumericos[posicion - 1] + vectorNumericos[posicion])/ 2.0);
            ///System.out.println("Posicion -> " + posicion + " valor de media -> " + mediana + " el resultado es " + vectorNumericos[posicion - 1] + " y " + vectorNumericos[posicion]);
        }
        if(cantidadVector%2 == 1) {
            posicion = (int)(aux1 + 0.5);
            mediana = (double)(vectorNumericos[posicion]);
            ///System.out.println("Posicion -> " + posicion + " valor de media -> " + mediana + " el resultado es " + vectorNumericos[posicion]);
        }
        return mediana;
    }
    
    public int calcularModa(int[] vectorNumericos) {
        int moda = 0, cantidadVector = vectorNumericos.length;
        int frecuencia, frecuenciaModa = 0;
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
    
    public double calcularDesviacionEstandar(int[] vectorNumericos) {
        double media, sumatoria = 0, cantidadVector = vectorNumericos.length, desviacionEstandar = 0;
        media = calcularMedia(vectorNumericos);
        for(int i = 0; i < cantidadVector; i++) {
            sumatoria += Math.pow(vectorNumericos[i] - media, 2);
        }
        desviacionEstandar = Math.sqrt(sumatoria / (double)cantidadVector);
        return desviacionEstandar;
    }
    
    //grafica de box plot para numéricos, las 4 funciones sirven para crear el gráfico
    public ArrayList<Double> crearListaValores(ArrayList<Integer> numericList) {
        ArrayList<Double> list = new ArrayList<Double>();
        for(int i = 0; i < numericList.size(); i++) {
            list.add(new Double(numericList.get(i)));
        }
        return list;
    }

    public BoxAndWhiskerXYDataset crearDataset(ArrayList<Integer> numericList) {
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

    public ChartFrame crearBoxPlot(String title, String attributeName, ArrayList<Integer> numericList) {
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

    
}
