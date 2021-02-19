import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class FrameFejhallgatokList extends JFrame {
    private    FejhallgatoDB db;
    private JList <Fejhallgato>fejhallgatoJList;
    private DefaultListModel<Fejhallgato> fejhallgatoModel;
    private JScrollPane fejhallgatoScrollPane;

    private JPanel mainPanel;
    private JButton btnTorol, btnHozzaad, btnModosit;


    public FrameFejhallgatokList(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        fejhallgatoModel= new DefaultListModel<>();

        try {
            db = new FejhallgatoDB();
            loadFejhallgatok();
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(null,"Hiba az adatbázishoz való kapcsolódáskor");
            dispose();
            return;
        }

        this.setTitle("Fejhallgatók");
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        mainPanel = (JPanel) (this.getContentPane());

        fejhallgatoJList = new JList<>(fejhallgatoModel);
        fejhallgatoJList.setFont(new Font("Courer New",Font.PLAIN,14));
        fejhallgatoJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fejhallgatoScrollPane = new JScrollPane(fejhallgatoJList);
        fejhallgatoScrollPane.setBounds(20,20,540,300);
        mainPanel.add(fejhallgatoScrollPane);

        btnHozzaad = new JButton("Hozzáad");
        btnHozzaad.setBounds(20,330,100,20);
        btnHozzaad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              FormFejhallgato insertform =  new FormFejhallgato(db);
              setVisible(false);
              insertform.addWindowListener(new WindowAdapter() {
                  @Override
                  public void windowClosed(WindowEvent e) {
                      setVisible(true);
                      try {

                          loadFejhallgatok();


                      } catch (SQLException throwables) {
                          JOptionPane.showMessageDialog(null,"Hiba a lista frissítésében"+ throwables.getMessage());

                      }

                      super.windowClosed(e);
                  }
              });

            }
        });
        mainPanel.add(btnHozzaad);

        btnTorol = new JButton("Töröl");
        btnTorol.setBounds(140,330,100,20);
        btnTorol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fejhallgatoJList.getSelectedIndex() < 0){
                    JOptionPane.showMessageDialog(null,"Törlés előtt válasszon ki egy elemt");
                    return;
                }
                Fejhallgato torlendo = fejhallgatoJList.getSelectedValue();
               int biztos = JOptionPane.showConfirmDialog(null,
                        "Biztos kivánja törölni az alábbi dolgozót:"+ torlendo.getNev());
               if (biztos == JOptionPane.YES_OPTION){
                   try{
                       boolean sikeres = db.deleteFejhallgato(torlendo.getId());
                       String uzenet = String.format("%s törlése",sikeres?"Sikeres":"Sikeretelen");

                       loadFejhallgatok();

                   }catch (SQLException throwables){
                       JOptionPane.showMessageDialog(null,"Sikertelen törlés\n"+throwables.getMessage());
                   }

               }
            }
        });
        mainPanel.add(btnTorol);

        btnModosit = new JButton("Módósít");
        btnModosit.setBounds(260,330,100,20);
        btnModosit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (fejhallgatoJList.getSelectedIndex() < 0){
                    JOptionPane.showMessageDialog(null,"Módosítás előtt válasszon ki egy elemt");
                    return;
                }
                Fejhallgato modositando = fejhallgatoJList.getSelectedValue();

                FormFejhallgato updateForm =  new FormFejhallgato(db,modositando);
                setVisible(false);
                updateForm.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setVisible(true);
                        try {

                            loadFejhallgatok();


                        } catch (SQLException throwables) {
                            JOptionPane.showMessageDialog(null,"Hiba a lista frissítésében"+ throwables.getMessage());

                        }

                        super.windowClosed(e);
                    }
                });
            }
        });
        mainPanel.add(btnModosit);

        this.setVisible(true);
    }

    private void loadFejhallgatok() throws  SQLException{
        ArrayList<Fejhallgato> fejhallgatoArrayList = db.getFejhallgatok();
        fejhallgatoModel.clear();
        for (Fejhallgato f:fejhallgatoArrayList
             ) {
            fejhallgatoModel.addElement(f);
        }
    }

}

