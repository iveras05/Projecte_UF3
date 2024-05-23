package nidal.lahlou.m03_uf3_pp_lahlou_vera.Controladors;

import com.google.zxing.WriterException;
import com.google.zxing.oned.CodaBarWriter;
import ion.Vera.Fitxers;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.transform.MatrixType;
import nidal.lahlou.m03_uf3_pp_lahlou_vera.Classes.Producte;
import nidal.lahlou.m03_uf3_pp_lahlou_vera.Classes.Usuari;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HelloController implements Serializable {
    private final CodaBarWriter cb = new CodaBarWriter();
    //Loggin
    @FXML
    private PasswordField TFContrasenya;
    @FXML
    private TextField TFUsuari;
    @FXML
    private Button BTNSubmit;

    //Afegir/Eliminar Persona
    @FXML
    private Button BTNAfegirPersona;
    @FXML
    private CheckBox CHKBXAdmin;
    @FXML
    private TextField TFUserName;
    @FXML
    private TextField TFUserPassword;
    @FXML
    private TextField TFNamePersona;
    @FXML
    private TextField TFSurnamePersona;
    @FXML
    private TextField TFCodiBarres;
    @FXML
    private CheckBox CHKBXAdminUserList;
    @FXML
    private TextArea TAUserList;
    @FXML
    private TextField TFProductDate;
    @FXML
    private TextField TFProductCodeBarresRemove;
    @FXML
    private TextField TFProductPrice;
    @FXML
    private TextField TFProductName;
    @FXML
    private TextField TFProductNameRemove;
    @FXML
    private Button TFProductSave;
    @FXML
    private Button BTNRemoveProduct;
    @FXML
    private Button BTNProductSearchRemove;
    @FXML
    private TextField TFProductDescription;
    @FXML
    private Button BTNProductScan;


    @FXML
    private Text TXTSecretAdmin;
    @FXML
    private TabPane TPPersona;
    @FXML
    private TabPane TBProducte;
    @FXML
    private Pane TXTAdmins;
    @FXML
    private Text TXTPersonaErr;
    @FXML
    private Button BTNListProduct;
    @FXML
    private Button BTNLlistarPersona;
    @FXML
    private Text TXTProducteErr;
    @FXML
    private TextArea TALlistaProductes;
    @FXML
    private Text TXTCodiErr;
    @FXML
    private Text TXTProducteElim;
    @FXML
    private ImageView IMGMain;


    static Fitxers f = new Fitxers();

    public void initialize() throws IOException {
        String nomFitxerAdmins = "administradors.dat";


        if (!f.existeix("administradors.dat")) {
            TXTSecretAdmin.setVisible(true);
            TXTSecretAdmin.setOnMouseClicked(e -> {
                TXTSecretAdmin.setVisible(false);
                TXTAdmins.setVisible(false);
                TPPersona.setVisible(true);
            });
        }
        afegirButtonUsuari();
    }


    @FXML
    public void afegirButtonUsuari() throws IOException {

        BTNAfegirPersona.setOnMouseClicked(e -> {
            if (TFNamePersona.getText().isEmpty() ||
                    TFSurnamePersona.getText().isEmpty() ||
                    TFUserName.getText().isEmpty() ||
                    TFUserPassword.getText().isEmpty()) {
                TXTPersonaErr.setVisible(true);
                return;
            } else {
                TXTPersonaErr.setVisible(false);

                String nom = TFNamePersona.getText();
                String cognom = TFSurnamePersona.getText();
                String nomUsuari = TFUserName.getText();
                String password = TFUserPassword.getText();


                Usuari u = new Usuari(nom, cognom, nomUsuari, password);

                String fitxer;

                if (CHKBXAdmin.isSelected()) {
                    fitxer = u.getFitxerAdministradors();
                } else {
                    fitxer = u.getFitxerUsuaris();
                }

                try {
                    u.guardaPersona(fitxer);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

                TFNamePersona.setText(null);
                TFSurnamePersona.setText(null);
                TFUserName.setText(null);
                TFUserPassword.setText(null);
                CHKBXAdmin.setSelected(false);
            }
        });
    }

    @FXML
    public void llistarButtonUsuari() throws IOException {

        Usuari u = new Usuari();
        BTNLlistarPersona.setOnMouseClicked(e -> {
            if (f.existeix("usuaris.dat") && !CHKBXAdminUserList.isSelected()) {
                //imprimeix usuaris.dat
                List<Usuari> lusuaris = new ArrayList<Usuari>();
                try {
                    lusuaris = u.retornaPersona("usuaris.dat");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                String userList = "";

                for (Usuari usu : lusuaris) {
                    userList += "-Nom: " + usu.getNom() + " -Cognom: " + usu.getCognom() + " -Nom d'usuari: " + usu.getNomUsuari() + "\n";
                    }

                TAUserList.setText(userList);

            } else if (f.existeix("usuaris.dat") && f.existeix("administradors.dat") && !CHKBXAdminUserList.isSelected()){
                //imprimeix els usuaris.dat
                List<Usuari> lusuaris = new ArrayList<Usuari>();
                try {
                    lusuaris = u.retornaPersona("usuaris.dat");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                String userList = "";

                for (Usuari usu : lusuaris) {
                    userList += "-Nom: " + usu.getNom() + " -Cognom: " + usu.getCognom() + " -Nom d'usuari: " + usu.getNomUsuari() + "\n";
                }
            } else if (f.existeix("administradors.dat") && CHKBXAdminUserList.isSelected()) {
                //imprimeix administradors.dat
                List<Usuari> ladmins = new ArrayList<Usuari>();
                try {
                    ladmins = u.retornaPersona("administradors.dat");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                String adminList = "";

                for (Usuari adm : ladmins) {
                    adminList += "-Nom: " + adm.getNom() + " -Cognom: " + adm.getCognom() + " -Nom d'usuari: " + adm.getNomUsuari() + "\n";
                }

                TAUserList.setText(adminList);
            }else{
                TAUserList.setText("NO HI HA CAP USUARI!");
                return;
            }
        });
    }

    public void loggin() throws IOException, InterruptedException, ClassNotFoundException {
        Usuari u = new Usuari();

        List<Usuari> lusuaris = new ArrayList<Usuari>();
        List<Usuari> ladmins = new ArrayList<Usuari>();

        lusuaris = u.retornaPersona("usuaris.dat");
        ladmins = u.retornaPersona("administradors.dat");
        String user = TFUsuari.getText();
        String password = TFContrasenya.getText();

        for (Usuari usuari : lusuaris) {
            if (usuari.getNomUsuari().equals(user) && usuari.getPassword().equals(password)) {
                TXTAdmins.setVisible(false);
                TBProducte.setVisible(true);
            }else{
                for (Usuari admin : ladmins){
                    if (admin.getNomUsuari().equals(user) && admin.getPassword().equals(password)) {
                        TXTAdmins.setVisible(false);
                        TPPersona.setVisible(true);
                    }else{
                        return;
                    }
                }
            }
        }
    }

    static Producte p = new Producte();
    public void escanejar() throws WriterException, IOException {

        if (TFCodiBarres.getText().isEmpty()){
            return;
        }else {
            String text = TFCodiBarres.getText();
            String image = "src/main/resources/nidal/lahlou/m03_uf3_pp_lahlou_vera/img/barcode.png";

            BarcodeFormat barcodeFormat = BarcodeFormat.CODE_128;
            int width = 350;
            int height = 200;

            BitMatrix bitMatrix = new MultiFormatWriter().encode(text,barcodeFormat,width,height);
            File outputFile = new File(image);

            MatrixToImageWriter.writeToPath(bitMatrix,"PNG",outputFile.toPath());

            Image img = new Image("file:" + image);

            IMGMain.setImage(img);
            TFProductName.setDisable(false);
            TFProductPrice.setDisable(false);
            TFProductDescription.setDisable(false);
            TFProductDate.setDisable(false);
            TFProductSave.setDisable(false);
        }
    }

    public void guardarButtonProducte(){
        TFProductSave.setOnMouseClicked(e -> {
            if (TFProductName.getText().isEmpty() ||
                    TFProductPrice.getText().isEmpty() ||
                    TFProductDescription.getText().isEmpty() ||
                    TFProductDate.getText().isEmpty()) {
                TXTProducteErr.setVisible(true);
                return;
            } else {
                TXTProducteErr.setVisible(false);

                String nomProducte = TFProductName.getText();
                String preuProducte = TFProductPrice.getText();
                String descProducte = TFProductDescription.getText();
                String dataProducte = TFProductDate.getText();
                String codiBarres = TFCodiBarres.getText();

                Producte p = new Producte(nomProducte,preuProducte,descProducte,dataProducte,codiBarres);

                String fitxer = p.getFitxerProductes();

                try {
                    p.guardaProducte(fitxer);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                TFProductName.setText(null);
                TFProductName.setDisable(true);

                TFProductPrice.setText(null);
                TFProductPrice.setDisable(true);

                TFProductDescription.setText(null);
                TFProductDescription.setDisable(true);

                TFProductDate.setText(null);
                TFProductDate.setDisable(true);

                TFCodiBarres.setText(null);

            }
        });
    }

    public void llistarButtonProducte(){

        String fitxer=p.getFitxerProductes();

        BTNListProduct.setOnMouseClicked(e -> {
            if (f.existeix(fitxer)){
                //visualitzar productes.dat
                List<Producte> lproductes = new ArrayList<Producte>();

                try {
                    lproductes = p.retornaProducte(fitxer);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                String productList = "";

                for (Producte pro : lproductes) {
                    productList += "-Nom: " + pro.getNomProducte() + " -Preu: " + pro.getPreuProducte() + " -Descripció: " + pro.getDescProducte() + "-Data: " + pro.getDataProducte() + "-Codi de Barres: " + pro.getCodiBarras() + "\n";
                }

                TALlistaProductes.setText(productList);

            }else{
                TALlistaProductes.setText("NO HI HA PRODUCTES!!");
            }
        });
    }

    public void buscarButtonProducte() throws IOException, ClassNotFoundException {


        List<Producte> llistaProducte = new ArrayList<>();
        llistaProducte = p.retornaProducte(p.getFitxerProductes());
        String codiBarres = TFProductCodeBarresRemove.getText();

        if (codiBarres.isEmpty()){
            return;
        }else{

            for (Producte pro:llistaProducte) {
                if (pro.getCodiBarras().equals(codiBarres)){
                    TFProductNameRemove.setDisable(false);
                    if (TXTCodiErr.isVisible()){
                        TXTCodiErr.setVisible(false);
                    }
                    TFProductNameRemove.setText(pro.getNomProducte());
                    BTNRemoveProduct.setDisable(false);
                    break;
                }else{
                    TXTCodiErr.setVisible(true);
                    TFProductNameRemove.setDisable(true);
                    BTNRemoveProduct.setDisable(true);
                }
            }
        }
        TXTProducteElim.setVisible(false);
    }

    public void eliminarButtonProducte() throws IOException, ClassNotFoundException {

        List<Producte> llistaP = p.retornaProducte(p.getFitxerProductes());
        boolean afegir = false;
        for (Producte prod: llistaP) {

            if (!TFProductNameRemove.getText().equals(prod.getNomProducte())){
                f.escriuObjecteFitxer(prod,p.getFitxerProductes(),afegir);
                afegir = true;
            }else{
                TXTProducteElim.setVisible(true);
            }
        }
        TFProductNameRemove.setText(null);
        TFProductCodeBarresRemove.setText(null);
        TFProductNameRemove.setDisable(true);
        BTNRemoveProduct.setDisable(true);

    }

}


