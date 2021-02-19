import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class FormFejhallgato  extends JFrame {
    FejhallgatoDB db;


    private JPanel mainPanel;
    private JLabel lblNev,lblFajta,lblAr,lblLeiras;
    private JTextField textFieldNev,textFieldLeiras,textFieldfajta;
    private JSpinner spinnerAr;

    private JButton btnHozzaad;
    private Fejhallgato fejhallgato;
    private boolean modosit;

    public FormFejhallgato(FejhallgatoDB db){
        this.db=db;

        modosit = false;

        this.setTitle("Fejhallgatók");
        this.setSize(300,260);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mainPanel = (JPanel)(this.getContentPane());

        lblNev = new JLabel("Név");
        lblNev.setBounds(20,20,100,20);
        mainPanel.add(lblNev);

        lblFajta = new JLabel("Fajta");
        lblFajta.setBounds(20,60,100,20);
        mainPanel.add(lblFajta);

        lblAr = new JLabel("Ár");
        lblAr.setBounds(20,100,100,20);
        mainPanel.add(lblAr);

        lblLeiras = new JLabel("Leírás");
        lblLeiras.setBounds(20,140,100,20);
        mainPanel.add(lblLeiras);

        textFieldNev = new JTextField();
        textFieldNev.setBounds(140,20, 140,20);
        mainPanel.add(textFieldNev);

        spinnerAr = new JSpinner();
        spinnerAr.setBounds(140,100, 140,20);
        mainPanel.add(spinnerAr);

        textFieldfajta = new JTextField();
        textFieldfajta.setBounds(140,60, 140,20);
        mainPanel.add(textFieldfajta);

        textFieldLeiras = new JTextField();
        textFieldLeiras.setBounds(140,140, 140,20);
        mainPanel.add(textFieldLeiras);

        btnHozzaad = new JButton("Hozzáad");
        btnHozzaad.setBounds(160,180,100,20);
        btnHozzaad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nev = textFieldNev.getText().trim();
                int ar = (int) spinnerAr.getValue();
                String fajta = textFieldfajta.getText().trim();
                String leiras = textFieldLeiras.getText().trim();
                if (nev.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Név megadása kötelező");
                    return;
                }
                if (ar == 0 || ar < 0){
                    JOptionPane.showMessageDialog(null,"Ár megadása kötelező");
                    return;
                }
                if (fajta.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Fajta megadása kötelező");
                    return;
                }
                if (leiras.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Leírás megadása kötelező");

                }
                if (modosit){
                    try {
                        int id  = fejhallgato.getId();
                        int siker = db.updateFejhallgato(id,nev, fajta, ar, leiras);
                        String uzenet = String.format("%s módosítás", siker > 0 ? "Sikeres": "Sikeretelen");
                        JOptionPane.showMessageDialog(null,uzenet);

                    }catch (SQLException throwables){
                        JOptionPane.showMessageDialog(null,"Adatbázis hiba");

                    }
                    dispose();
                }else {

                    try {
                        int siker = db.insertFejhallgato(nev, fajta, ar, leiras);
                        String uzenet = String.format("%s hozzáadása", siker > 0 ? "Sikeres": "Sikeretelen");
                        JOptionPane.showMessageDialog(null,uzenet);

                    }catch (SQLException throwables){
                        JOptionPane.showMessageDialog(null,"Adatbázis hiba");

                    }
                }

            }
        });
        mainPanel.add(btnHozzaad);

        this.setVisible(true);

    }

    public FormFejhallgato(FejhallgatoDB db, Fejhallgato f){
        this(db);
        this.fejhallgato = f;
        this.btnHozzaad.setText("Módosít");
        this.modosit = true;
        this.textFieldNev.setText(fejhallgato.getNev());
        spinnerAr.setValue(fejhallgato.getAr());
        this.textFieldfajta.setText(fejhallgato.getFajta());
        this.textFieldLeiras.setText(fejhallgato.getLeiras());



    }
}
