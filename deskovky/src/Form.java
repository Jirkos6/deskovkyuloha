import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Form extends JFrame{
    private JPanel JPane;
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenu menuHelp;
    private JMenuItem miOpenFile;
    private JTextField nazev;
    private JButton tlacitkoZpet;
    private JButton tlacitkoDalsi;
    private JLabel nazevLabel;
    private JLabel oblibenostLabel;
    private JLabel titulekLabel;
    private JRadioButton oblibenost1;
    private JRadioButton oblibenost2;
    private JRadioButton oblibenost3;
    private JCheckBox zakoupeno;
    private JSlider slider;
    private ButtonGroup skupina;
    private int indexAktualniDeskovky = 0;
    private List<Deskovka> seznam = new ArrayList<>();
    private ZaverecnaUloha soubory = new ZaverecnaUloha();


    public Form() {
        skupina = new ButtonGroup();
        skupina.add(oblibenost1);
        skupina.add(oblibenost2);
        skupina.add(oblibenost3);
        seznam = soubory.ziskejSeznamDeskovekZeSouboru();
        nactiData(indexAktualniDeskovky);
        initComponets();


        tlacitkoDalsi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aktualizujData();
                indexAktualniDeskovky++;
                nactiData(indexAktualniDeskovky);

            }
        });
        tlacitkoZpet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aktualizujData();
                indexAktualniDeskovky--;
                nactiData(indexAktualniDeskovky);
            }
        });

    }

    public void nactiData(int index){
        //Nastavování tlačítek
        if(index == 0){
            tlacitkoZpet.setEnabled(false);
            tlacitkoDalsi.setEnabled(true);
        }
        else if(index == seznam.size()-1){
            tlacitkoZpet.setEnabled(true);
            tlacitkoDalsi.setEnabled(false);
        }
        else{
            tlacitkoDalsi.setEnabled(true);
            tlacitkoZpet.setEnabled(true);
        }

        //Podmínky pro vyhnutí se neexistujícím indexům
        if(index < 0){
            indexAktualniDeskovky = 0;
            return;
        }else if(index >= seznam.size()){
            indexAktualniDeskovky = seznam.size() - 1;
            return;
        }

        Deskovka aktualniDeskovka = seznam.get(index);
        nazev.setText(aktualniDeskovka.getNazev());
        zakoupeno.setSelected(aktualniDeskovka.isZakoupeno());
        slider.setValue(aktualniDeskovka.getOblibenost());
    }





    public void aktualizujData(){
        seznam = soubory.ziskejSeznamDeskovekZeSouboru();
        String nazevDeskovky = nazev.getText();
        boolean bylaZakoupena = zakoupeno.isSelected();
        int oblibenost = slider.getValue();
        Deskovka novaDeskovka = new Deskovka(nazevDeskovky, bylaZakoupena, oblibenost);
        seznam.set(indexAktualniDeskovky, novaDeskovka);
    }
    private void initComponets(){

        JMenuBar jMenuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");

        JMenuItem openItem = new JMenuItem("Open");

        menu.add(openItem);
        menu.addSeparator();

        JMenuItem clearData = new JMenuItem("Clear");


        menu.add(clearData);

        jMenuBar.add(menu);
        setJMenuBar(jMenuBar);
        openItem.setMnemonic(KeyEvent.VK_F);

    }


    public static void main(String[] args) {
        Form form = new Form();
        form.pack();
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setVisible(true);
        form.setContentPane(form.JPane);









    }
}
