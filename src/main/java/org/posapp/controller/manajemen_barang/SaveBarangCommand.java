package org.posapp.controller.manajemen_barang;

import org.posapp.controller.Command;
import org.posapp.view.ManajemenBarang.DetailBarangView;
import org.posapp.view.ManajemenBarang.ManajemenBarangView;

public class SaveBarangCommand implements Command {
    private ManajemenBarangView view;
    public SaveBarangCommand(ManajemenBarangView view){
        this.view = view;
    }
    @Override
    public void execute() {
        DetailBarangView temp = (DetailBarangView) view.getLayout().getChildren().get(1);
        view.getController().updateBarang(
                temp.getItem(),
                temp.getNamaField().getText(),
                temp.getKatField().getText(),
                Integer.parseInt(temp.getStokField().getText()),
                Integer.parseInt(temp.getBeliField().getText()),
                Integer.parseInt(temp.getJualField().getText()),
                "url"
        );
    }
}
