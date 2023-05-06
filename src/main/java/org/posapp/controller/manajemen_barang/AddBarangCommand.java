package org.posapp.controller.manajemen_barang;

import org.posapp.controller.Command;
import org.posapp.model.Barang;
import org.posapp.view.ManajemenBarang.DetailBarangView;
import org.posapp.view.ManajemenBarang.ManajemenBarangView;

public class AddBarangCommand implements Command {
    private ManajemenBarangView view;
    public AddBarangCommand(ManajemenBarangView view){
        this.view = view;
    }
    @Override
    public void execute() {
        view.getController().addBarang();
    }
}
