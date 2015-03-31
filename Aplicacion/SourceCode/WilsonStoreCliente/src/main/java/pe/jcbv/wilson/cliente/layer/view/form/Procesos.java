package pe.jcbv.wilson.cliente.layer.view.form;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import pe.jcbv.wilson.cliente.layer.controller.ProcesosController;
import pe.jcbv.wilson.cliente.layer.view.util.MessageWin;
import pe.jcbv.wilson.cliente.layer.view.util.Sesion;
import pe.jcbv.wilson.cliente.domain.Detalle;
import pe.jcbv.wilson.cliente.domain.Usuario;
import pe.jcbv.wilson.cliente.domain.Venta;
import sun.awt.DefaultMouseInfoPeer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Calendar;
import javax.swing.ImageIcon;
import java.awt.Color;
import pe.jcbv.wilson.cliente.domain.Articulo;
import pe.jcbv.wilson.cliente.layer.export.ExportPDF;
import pe.jcbv.wilson.cliente.layer.service.ArticuloService;
import pe.jcbv.wilson.cliente.layer.service.ClienteService;

public class Procesos extends JDialog {

    protected static JTable tablaVentaArt;
    private JButton btnLimpiar;
    private JButton btnAgregar;
    private JButton btnQuitar;
    protected static JTextField txtNombreCli;
    private JTextField txtFecha;
    private static JTextField txtSubtotal;
    private static JTextField txtImpuesto;
    private static JTextField txtTotal;
    protected static String codigoCli;
    protected static int cantidad;
    private static double subtotal;
    private static double impuesto;
    private static double total;
    private JButton btnBuscarCli;
    private JLabel lblNewLabel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Procesos dialog = new Procesos();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the dialog.
     */
    public Procesos() {
        setTitle("REGISTRO");
        setBounds(100, 100, 850, 579);
        getContentPane().setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setBounds(0, 0, 834, 541);
        getContentPane().add(tabbedPane);

        JPanel panel = new JPanel();
        tabbedPane.addTab("Venta", null, panel, null);
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 123, 768, 257);
        panel.add(scrollPane);

        tablaVentaArt = new JTable();
        tablaVentaArt.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "C\u00F3digo", "Nombre", "Descripci\u00F3n", "Cant.", "P. Unit.", "Importe"
                }
        ) {
            Class[] columnTypes = new Class[]{
                String.class, String.class, String.class, Integer.class, Double.class, Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        tablaVentaArt.getColumnModel().getColumn(0).setResizable(false);
        tablaVentaArt.getColumnModel().getColumn(0).setPreferredWidth(15);
        tablaVentaArt.getColumnModel().getColumn(1).setResizable(false);
        tablaVentaArt.getColumnModel().getColumn(1).setPreferredWidth(140);
        tablaVentaArt.getColumnModel().getColumn(2).setResizable(false);
        tablaVentaArt.getColumnModel().getColumn(2).setPreferredWidth(260);
        tablaVentaArt.getColumnModel().getColumn(3).setResizable(false);
        tablaVentaArt.getColumnModel().getColumn(3).setPreferredWidth(15);
        tablaVentaArt.getColumnModel().getColumn(4).setResizable(false);
        tablaVentaArt.getColumnModel().getColumn(4).setPreferredWidth(30);
        tablaVentaArt.getColumnModel().getColumn(5).setResizable(false);
        tablaVentaArt.getColumnModel().getColumn(5).setPreferredWidth(30);
        scrollPane.setViewportView(tablaVentaArt);

        btnLimpiar = new JButton("");
        btnLimpiar.setIcon(new ImageIcon(Procesos.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/limpiar.png")));
        btnLimpiar.setToolTipText("Limpiar");
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                limpiarTabla();
            }
        });
        btnLimpiar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        btnLimpiar.setBounds(31, 418, 56, 56);
        panel.add(btnLimpiar);

        btnAgregar = new JButton("");
        btnAgregar.setToolTipText("Agregar");
        btnAgregar.setIcon(new ImageIcon(Procesos.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/agregar.png")));
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				//SeleccionaArticulo.main( null );

                SeleccionaArticulo view = new SeleccionaArticulo();
                view.setModal(true);
                view.setVisible(true);
            }
        });
        btnAgregar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        btnAgregar.setBounds(111, 418, 56, 56);
        panel.add(btnAgregar);

        btnQuitar = new JButton("");
        btnQuitar.setToolTipText("Quitar");
        btnQuitar.setIcon(new ImageIcon(Procesos.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/quitar.png")));
        btnQuitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                accionQuitarFila();
            }
        });
        btnQuitar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        btnQuitar.setBounds(191, 418, 56, 56);
        panel.add(btnQuitar);

        JButton btnSalir = new JButton("");
        btnSalir.setIcon(new ImageIcon(Procesos.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/cerrar.png")));
        btnSalir.setToolTipText("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        btnSalir.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        btnSalir.setBounds(271, 418, 56, 56);
        panel.add(btnSalir);

        JLabel lblNombreCliente = new JLabel("CLIENTE");
        lblNombreCliente.setForeground(Color.WHITE);
        lblNombreCliente.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombreCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNombreCliente.setBounds(62, 34, 96, 14);
        panel.add(lblNombreCliente);

        txtNombreCli = new JTextField();
        txtNombreCli.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtNombreCli.setBounds(168, 29, 259, 25);
        panel.add(txtNombreCli);
        txtNombreCli.setColumns(10);

        JLabel lblFecha = new JLabel("FECHA");
        lblFecha.setForeground(Color.WHITE);
        lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFecha.setBounds(88, 80, 70, 14);
        panel.add(lblFecha);

        Calendar c = Calendar.getInstance();
        String d = String.format("%02d", c.get(Calendar.DAY_OF_MONTH));
        String m = String.format("%02d", c.get(Calendar.MONTH));
        String a = String.format("%02d", c.get(Calendar.YEAR));
        String fecha = d + " / " + m + " / " + a;

        txtFecha = new JTextField(fecha);
        txtFecha.setHorizontalAlignment(SwingConstants.CENTER);
        txtFecha.setFont(new Font("Tahoma", Font.BOLD, 13));
        txtFecha.setEditable(false);
        txtFecha.setColumns(10);
        txtFecha.setBounds(168, 77, 131, 20);
        panel.add(txtFecha);

        JButton btnGrabar = new JButton("");
        btnGrabar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        btnGrabar.setIcon(new ImageIcon(Procesos.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/grabar.png")));
        btnGrabar.setToolTipText("Grabar");
        btnGrabar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionGrabar();
            }
        });
        btnGrabar.setBounds(649, 34, 81, 57);
        panel.add(btnGrabar);

        txtSubtotal = new JTextField();
        txtSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
        txtSubtotal.setEditable(false);
        txtSubtotal.setForeground(Color.WHITE);
        txtSubtotal.setBackground(Color.BLACK);
        txtSubtotal.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtSubtotal.setBounds(698, 397, 100, 25);
        panel.add(txtSubtotal);
        txtSubtotal.setColumns(10);

        txtImpuesto = new JTextField();
        txtImpuesto.setHorizontalAlignment(SwingConstants.RIGHT);
        txtImpuesto.setBackground(Color.BLACK);
        txtImpuesto.setForeground(Color.WHITE);
        txtImpuesto.setEditable(false);
        txtImpuesto.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtImpuesto.setColumns(10);
        txtImpuesto.setBounds(698, 435, 100, 25);
        panel.add(txtImpuesto);

        txtTotal = new JTextField();
        txtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        txtTotal.setEditable(false);
        txtTotal.setBackground(Color.BLACK);
        txtTotal.setForeground(Color.WHITE);
        txtTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtTotal.setColumns(10);
        txtTotal.setBounds(698, 472, 100, 25);
        panel.add(txtTotal);

        JLabel lblSubtotal = new JLabel("SUB-TOTAL");
        lblSubtotal.setForeground(Color.BLACK);
        lblSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSubtotal.setFont(new Font("Arial Black", Font.BOLD, 16));
        lblSubtotal.setBounds(554, 399, 131, 20);
        panel.add(lblSubtotal);

        JLabel lblIgv = new JLabel("I.G.V.");
        lblIgv.setForeground(Color.BLACK);
        lblIgv.setHorizontalAlignment(SwingConstants.RIGHT);
        lblIgv.setFont(new Font("Arial Black", Font.BOLD, 16));
        lblIgv.setBounds(598, 437, 87, 20);
        panel.add(lblIgv);

        JLabel lblTotal = new JLabel("TOTAL");
        lblTotal.setForeground(Color.BLACK);
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotal.setFont(new Font("Arial Black", Font.BOLD, 16));
        lblTotal.setBounds(598, 474, 87, 20);
        panel.add(lblTotal);

        btnBuscarCli = new JButton("");
        btnBuscarCli.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        btnBuscarCli.setIcon(new ImageIcon(Procesos.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/cliente.png")));
        btnBuscarCli.setToolTipText("Cliente");
        btnBuscarCli.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SeleccionaCliente view = new SeleccionaCliente();
                view.setModal(true);
                view.setVisible(true);
            }
        });
        btnBuscarCli.setBounds(439, 21, 46, 41);
        panel.add(btnBuscarCli);

        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Procesos.class.getResource("/pe/jcbv/wilson/cliente/layer/view/img/fondogris.jpg")));
        lblNewLabel.setBounds(0, 0, 829, 513);
        panel.add(lblNewLabel);

    }

    private void limpiarTabla() {
        DefaultTableModel tabla;
        tabla = (DefaultTableModel) tablaVentaArt.getModel();
        tabla.setRowCount(0);

        txtSubtotal.setText("");
        txtImpuesto.setText("");
        txtTotal.setText("");
    }

    protected static void calculaSubTotal() {
        subtotal = 0.0;

        for (int i = 0; i < tablaVentaArt.getRowCount(); i++) {
            subtotal += (double) tablaVentaArt.getValueAt(i, 5);
        }

        txtSubtotal.setText(String.format(Locale.US, "%,.2f", subtotal));
    }

    protected static void calculaImpuesto() {
        impuesto = 0.18 * subtotal;
        txtImpuesto.setText(String.format(Locale.US, "%,.2f", impuesto));
    }

    protected static void calculaTotal() {
        total = subtotal + impuesto;
        txtTotal.setText(String.format(Locale.US, "%,.2f", total));
    }

    private String obtenerCodigoUsuario() {
        Usuario user = (Usuario) Sesion.get("usuario");

        return user.getEmp_id();
    }

    public void accionGrabar() {
        try {
            ProcesosController controller = new ProcesosController();
            List< Detalle> lista = new ArrayList< Detalle>();
            Venta venta = new Venta();
            Detalle detalle;

            for (int i = 0; i < tablaVentaArt.getRowCount(); i++) {
                detalle = new Detalle();
                detalle.setArt_id(tablaVentaArt.getValueAt(i, 0).toString());
                detalle.setDet_cant((int) tablaVentaArt.getValueAt(i, 3));
                detalle.setDet_precio((double) tablaVentaArt.getValueAt(i, 5));
                detalle.setArticulo(new ArticuloService().consultarPorCodigo(detalle.getArt_id()));
                lista.add(detalle);
            }

            venta.setVen_subtotal(subtotal);
            venta.setVen_impuesto(impuesto);
            venta.setVen_total(total);
            venta.setEmp_id(obtenerCodigoUsuario());
            venta.setCli_id(codigoCli);
            venta.setDetalle(lista);
            venta.setFecha(txtFecha.getText());
            controller.grabarVenta(venta);
            MessageWin.showInfo("VENTA " + venta.getVen_id() + " REGISTRADA, IMPRIMIENDO...");
            new ExportPDF().print(venta, new ClienteService().consultarPorCodigo(codigoCli));
                    JOptionPane.showMessageDialog(this, "VENTA IMPRESA");
                    dispose();
//            limpiarTabla();

        } catch (Exception e) {
            e.printStackTrace();
            MessageWin.showError(e.getMessage());
        }
    }

    public void accionQuitarFila() {
        if (tablaVentaArt.getRowCount() == 0) {
            MessageWin.showError("LA TABLA NO TIENE FILAS.");
            return;
        }

        int fila = tablaVentaArt.getSelectedRow();

        if (fila == -1) {
            MessageWin.showError("NINGUNA FILA SELECCIONADA.");
            return;
        }

        DefaultTableModel tabla;
        tabla = (DefaultTableModel) tablaVentaArt.getModel();
        tabla.removeRow(tablaVentaArt.getSelectedRow());
        tablaVentaArt.addRowSelectionInterval(0, 0);
        tabla = null;

        calculaSubTotal();
        calculaImpuesto();
        calculaTotal();
    }
}
