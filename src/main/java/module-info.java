module nidal.lahlou.m03_uf3_pp_lahlou_vera {
    requires javafx.controls;
    requires javafx.fxml;
    requires Fitxers;
    requires com.google.zxing;
    requires com.google.zxing.javase;


    opens nidal.lahlou.m03_uf3_pp_lahlou_vera to javafx.fxml;
    exports nidal.lahlou.m03_uf3_pp_lahlou_vera;

    exports nidal.lahlou.m03_uf3_pp_lahlou_vera.Controladors;
    opens nidal.lahlou.m03_uf3_pp_lahlou_vera.Controladors to javafx.fxml;
    exports nidal.lahlou.m03_uf3_pp_lahlou_vera.Classes;
    opens nidal.lahlou.m03_uf3_pp_lahlou_vera.Classes to javafx.fxml;
}