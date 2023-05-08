package org.posapp.controller.command;

import javafx.scene.control.Alert;
import org.posapp.controller.command.Command;
import org.posapp.controller.manajemen_barang.InvalidInputException;
import org.posapp.view.ManajemenBarang.DetailBarangView;
import org.posapp.view.ManajemenBarang.ManajemenBarangView;

public class SaveBarangCommand implements Command {
    private ManajemenBarangView view;
    public SaveBarangCommand(ManajemenBarangView view){
        this.view = view;
    }
    @Override
    public void execute() {
        try {
            DetailBarangView temp = (DetailBarangView) view.getLayout().getChildren().get(1);
            view.getController().updateBarang(
                    temp.getItem(),
                    temp.getNamaField().getText(),
                    temp.getKatField().getText(),
                    temp.getStokField().getText(),
                    temp.getJualField().getText(),
                    temp.getBeliField().getText(),
                    temp.getSelectedFile()
            );
        } catch (InvalidInputException err) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText(err.getMessage());

            alert.showAndWait();
        }
    }
}
