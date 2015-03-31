/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.jcbv.wilson.cliente.layer.export;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JROrigin;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignFrame;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignLine;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.BandTypeEnum;
import net.sf.jasperreports.engine.type.CalculationEnum;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.PositionTypeEnum;
import net.sf.jasperreports.engine.type.ResetTypeEnum;
import net.sf.jasperreports.engine.xml.JasperDesignFactory;
import pe.jcbv.wilson.cliente.domain.Articulo;
import pe.jcbv.wilson.cliente.domain.Cliente;
import pe.jcbv.wilson.cliente.domain.Venta;
import pe.jcbv.wilson.cliente.layer.service.ProcesosService;
import pe.jcbv.wilson.cliente.layer.view.form.Procesos;

/**
 *
 * @author joseluis
 */
public class ExportPDF {

    private static JasperDesign getJasperDesign(String idVenta) throws JRException {
        //JasperDesign
        JasperDesign jasperDesign = new JasperDesign();
        jasperDesign.setName("VENTA_" + idVenta);
        jasperDesign.setPageWidth(595);
        jasperDesign.setPageHeight(842);
        jasperDesign.setColumnWidth(515);
        jasperDesign.setColumnSpacing(0);
        jasperDesign.setLeftMargin(40);
        jasperDesign.setRightMargin(40);
        jasperDesign.setTopMargin(50);
        jasperDesign.setBottomMargin(50);

        //Fonts
        JRDesignStyle normalStyle = new JRDesignStyle();
        normalStyle.setName("Sans_Normal");
        normalStyle.setDefault(true);
        normalStyle.setFontName("DejaVu Sans");
        normalStyle.setFontSize(12);
        normalStyle.setPdfFontName("Helvetica");
        normalStyle.setPdfEncoding("Cp1252");
        normalStyle.setPdfEmbedded(false);
        jasperDesign.addStyle(normalStyle);

        JRDesignStyle boldStyle = new JRDesignStyle();
        boldStyle.setName("Sans_Bold");
        boldStyle.setFontName("DejaVu Sans");
        boldStyle.setFontSize(12);
        boldStyle.setBold(true);
        boldStyle.setPdfFontName("Helvetica-Bold");
        boldStyle.setPdfEncoding("Cp1252");
        boldStyle.setPdfEmbedded(false);
        jasperDesign.addStyle(boldStyle);

        JRDesignStyle italicStyle = new JRDesignStyle();
        italicStyle.setName("Sans_Italic");
        italicStyle.setFontName("DejaVu Sans");
        italicStyle.setFontSize(12);
        italicStyle.setItalic(true);
        italicStyle.setPdfFontName("Helvetica-Oblique");
        italicStyle.setPdfEncoding("Cp1252");
        italicStyle.setPdfEmbedded(false);
        jasperDesign.addStyle(italicStyle);
        
        //Parameters
        JRDesignParameter parameter = new JRDesignParameter();
        parameter.setName("apaterno");
        parameter.setValueClass(java.lang.String.class);
        jasperDesign.addParameter(parameter);

        parameter = new JRDesignParameter();
        parameter.setName("amaterno");
        parameter.setValueClass(java.lang.String.class);
        jasperDesign.addParameter(parameter);

        parameter = new JRDesignParameter();
        parameter.setName("nombre");
        parameter.setValueClass(java.lang.String.class);
        jasperDesign.addParameter(parameter);

        parameter = new JRDesignParameter();
        parameter.setName("fecha");
        parameter.setValueClass(java.lang.String.class);
        jasperDesign.addParameter(parameter);
        parameter = new JRDesignParameter();
        parameter.setName("subtotal");
        parameter.setValueClass(java.lang.Double.class);
        jasperDesign.addParameter(parameter);
        parameter = new JRDesignParameter();
        parameter.setName("igv");
        parameter.setValueClass(java.lang.Double.class);
        jasperDesign.addParameter(parameter);
        parameter = new JRDesignParameter();
        parameter.setName("total");
        parameter.setValueClass(java.lang.Double.class);
        jasperDesign.addParameter(parameter);
        //Fields
        JRDesignField field = new JRDesignField();
        field.setName("art_id");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("articulo");
        field.setValueClass(Articulo.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("det_cant");
        field.setValueClass(java.lang.Integer.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("det_precio");
        field.setValueClass(java.lang.Double.class);
        jasperDesign.addField(field);

        //Title
        JRDesignBand band = new JRDesignBand();
        band.setHeight(90);
        JRDesignLine line = new JRDesignLine();
        line.setX(0);
        line.setY(0);
        line.setWidth(515);
        line.setHeight(0);
        band.addElement(line);
        JRDesignTextField textField = new JRDesignTextField();
        textField.setBlankWhenNull(true);
        textField.setX(0);
        textField.setY(10);
        textField.setWidth(515);
        textField.setHeight(30);
        textField.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        textField.setStyle(normalStyle);
        textField.setFontSize(22);
        JRDesignExpression expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("\"VENTA " + idVenta + "\"");
        textField.setExpression(expression);
        band.addElement(textField);
        JRDesignStaticText staticText = new JRDesignStaticText();
        staticText.setX(0);
        staticText.setY(50);
        staticText.setWidth(70);
        staticText.setHeight(15);
        staticText.setStyle(boldStyle);
        staticText.setText("CLIENTE : ");
        band.addElement(staticText);
        textField = new JRDesignTextField();
        textField.setX(75);
        textField.setY(50);
        textField.setWidth(300);
        textField.setHeight(15);
        textField.setStyle(boldStyle);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.Integer.class);
        expression.setText("$P{nombre} + \" \" + $P{apaterno} + \" \" + $P{amaterno}");
        textField.setExpression(expression);
        band.addElement(textField);
        staticText = new JRDesignStaticText();
        staticText.setX(0);
        staticText.setY(70);
        staticText.setWidth(70);
        staticText.setHeight(15);
        staticText.setStyle(boldStyle);
        staticText.setText("FECHA : ");
        band.addElement(staticText);
        textField = new JRDesignTextField();
        textField.setX(75);
        textField.setY(70);
        textField.setWidth(300);
        textField.setHeight(15);
        textField.setStyle(boldStyle);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.Integer.class);
        expression.setText("$P{fecha}");
        textField.setExpression(expression);
        band.addElement(textField);
        jasperDesign.setTitle(band);

        //Page header
        band = new JRDesignBand();
        band.setHeight(20);
        JRDesignFrame frame = new JRDesignFrame();
        frame.setX(0);
        frame.setY(5);
        frame.setWidth(515);
        frame.setHeight(15);
        frame.setForecolor(new Color(0x33, 0x33, 0x33));
        frame.setBackcolor(new Color(0x33, 0x33, 0x33));
        frame.setMode(ModeEnum.OPAQUE);
        band.addElement(frame);
        staticText = new JRDesignStaticText();
        staticText.setX(0);
        staticText.setY(0);
        staticText.setWidth(60);
        staticText.setHeight(15);
        staticText.setForecolor(Color.white);
        staticText.setBackcolor(new Color(0x33, 0x33, 0x33));
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
        staticText.setStyle(boldStyle);
        staticText.setText("CODIGO");
        frame.addElement(staticText);
        staticText = new JRDesignStaticText();
        staticText.setX(65);
        staticText.setY(0);
        staticText.setWidth(245);
        staticText.setHeight(15);
        staticText.setForecolor(Color.white);
        staticText.setBackcolor(new Color(0x33, 0x33, 0x33));
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setStyle(boldStyle);
        staticText.setText("NOMBRE");
        frame.addElement(staticText);
        staticText = new JRDesignStaticText();
        staticText.setX(310);
        staticText.setY(0);
        staticText.setWidth(40);
        staticText.setHeight(15);
        staticText.setForecolor(Color.white);
        staticText.setBackcolor(new Color(0x33, 0x33, 0x33));
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
        staticText.setStyle(boldStyle);
        staticText.setText("CANT.");
        frame.addElement(staticText);
        staticText = new JRDesignStaticText();
        staticText.setX(350);
        staticText.setY(0);
        staticText.setWidth(75);
        staticText.setHeight(15);
        staticText.setForecolor(Color.white);
        staticText.setBackcolor(new Color(0x33, 0x33, 0x33));
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
        staticText.setStyle(boldStyle);
        staticText.setText("P.UNIT.");
        frame.addElement(staticText);
        staticText = new JRDesignStaticText();
        staticText.setX(425);
        staticText.setY(0);
        staticText.setWidth(90);
        staticText.setHeight(15);
        staticText.setForecolor(Color.white);
        staticText.setBackcolor(new Color(0x33, 0x33, 0x33));
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
        staticText.setStyle(boldStyle);
        staticText.setText("IMPORTE");
        frame.addElement(staticText);
        jasperDesign.setPageHeader(band);

        //Column header
        band = new JRDesignBand();
        jasperDesign.setColumnHeader(band);

        //Detail
        band = new JRDesignBand();
        band.setHeight(20);
        textField = new JRDesignTextField();
        textField.setX(0);
        textField.setY(4);
        textField.setWidth(60);
        textField.setHeight(15);
        textField.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
        textField.setStyle(normalStyle);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.Integer.class);
        expression.setText("$F{art_id}");
        textField.setExpression(expression);
        band.addElement(textField);
        textField = new JRDesignTextField();
        textField.setStretchWithOverflow(true);
        textField.setX(65);
        textField.setY(4);
        textField.setWidth(245);
        textField.setHeight(15);
        textField.setPositionType(PositionTypeEnum.FLOAT);
        textField.setStyle(normalStyle);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{articulo}.getArt_nombre()");
        textField.setExpression(expression);
        band.addElement(textField);
        textField = new JRDesignTextField();
        textField.setX(310);
        textField.setY(4);
        textField.setWidth(40);
        textField.setHeight(15);
        textField.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
        textField.setStyle(normalStyle);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{det_cant}");
        textField.setExpression(expression);
        band.addElement(textField);
        textField = new JRDesignTextField();
        textField.setX(350);
        textField.setY(4);
        textField.setWidth(75);
        textField.setHeight(15);
        textField.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
        textField.setStyle(normalStyle);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{articulo}.getArt_pventa()");
        textField.setExpression(expression);
        band.addElement(textField);
        textField = new JRDesignTextField();
        textField.setX(425);
        textField.setY(4);
        textField.setWidth(90);
        textField.setHeight(15);
        textField.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
        textField.setStyle(normalStyle);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{det_precio}");
        textField.setExpression(expression);
        band.addElement(textField);
        line = new JRDesignLine();
        line.setX(0);
        line.setY(19);
        line.setWidth(515);
        line.setHeight(0);
        line.setForecolor(new Color(0x80, 0x80, 0x80));
        line.setPositionType(PositionTypeEnum.FLOAT);
        band.addElement(line);
        ((JRDesignSection) jasperDesign.getDetailSection()).addBand(band);

        //Column footer
        band = new JRDesignBand();
        jasperDesign.setColumnFooter(band);

        //Page footer
        band = new JRDesignBand();
        jasperDesign.setPageFooter(band);

        //Summary
        band = new JRDesignBand();
        band.setHeight(110);
        line = new JRDesignLine();
        line.setX(0);
        line.setY(-1);
        line.setWidth(515);
        line.setHeight(0);
        band.addElement(line);
        staticText = new JRDesignStaticText();
        staticText.setX(350);
        staticText.setY(10);
        staticText.setWidth(100);
        staticText.setHeight(15);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
        staticText.setStyle(boldStyle);
        staticText.setText("SUB-TOTAL : ");
        band.addElement(staticText);
        textField = new JRDesignTextField();
        textField.setX(450);
        textField.setY(10);
        textField.setWidth(65);
        textField.setHeight(15);
        textField.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
        textField.setStyle(boldStyle);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.Integer.class);
        expression.setText("$P{subtotal}");
        textField.setExpression(expression);
        band.addElement(textField);
        //igv
        staticText = new JRDesignStaticText();
        staticText.setX(350);
        staticText.setY(30);
        staticText.setWidth(100);
        staticText.setHeight(15);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
        staticText.setStyle(boldStyle);
        staticText.setText("IGV : ");
        band.addElement(staticText);
        textField = new JRDesignTextField();
        textField.setX(450);
        textField.setY(30);
        textField.setWidth(65);
        textField.setHeight(15);
        textField.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
        textField.setStyle(boldStyle);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.Integer.class);
        expression.setText("$P{igv}");
        textField.setExpression(expression);
        band.addElement(textField);
        //total
        staticText = new JRDesignStaticText();
        staticText.setX(350);
        staticText.setY(50);
        staticText.setWidth(100);
        staticText.setHeight(15);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
        staticText.setStyle(boldStyle);
        staticText.setText("TOTAL : ");
        band.addElement(staticText);
        textField = new JRDesignTextField();
        textField.setX(450);
        textField.setY(50);
        textField.setWidth(65);
        textField.setHeight(15);
        textField.setHorizontalAlignment(HorizontalAlignEnum.RIGHT);
        textField.setStyle(boldStyle);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.Integer.class);
        expression.setText("$P{total}");
        textField.setExpression(expression);
        band.addElement(textField);
        JRDesignSection footer = new JRDesignSection(new JROrigin(BandTypeEnum.LAST_PAGE_FOOTER));
        footer.addBand(band);
        jasperDesign.setSummary(band);

        return jasperDesign;
    }

    public void print(Venta venta, Cliente cliente) throws JRException {
        long start = System.currentTimeMillis();
        JasperDesign jasperDesign = getJasperDesign(venta.getVen_id());
//        JasperCompileManager.compileReportToFile(jasperDesign, "NoXmlDesignReport.jasper");
        System.err.println("Compile time : " + (System.currentTimeMillis() - start));
        JasperReport compileReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> params = new HashMap<>();
        params.put("apaterno", cliente.getCli_paterno());
        params.put("amaterno", cliente.getCli_materno());
        params.put("nombre", cliente.getCli_nombre());
        params.put("fecha", venta.getFecha());
        params.put("subtotal",venta.getVen_subtotal());
        params.put("igv", venta.getVen_impuesto());
        params.put("total", venta.getVen_total());

        JasperPrint fillReport = JasperFillManager.fillReport(compileReport, params, new JRBeanCollectionDataSource(venta.getDetalle()));
        JasperPrintManager.printReport(fillReport, Boolean.FALSE);
    }
}
