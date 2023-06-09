package org.posapp.controller.command;

import org.posapp.controller.command.Command;
import org.posapp.view.ManajemenBarang.DetailBarangView;
import org.posapp.view.ManajemenBarang.ManajemenBarangView;

public class DeleteBarangCommand implements Command {
    ManajemenBarangView view;
    public DeleteBarangCommand(ManajemenBarangView _view){
        view = _view;
    }
    @Override
    public void execute() {
        DetailBarangView temp = (DetailBarangView) view.getLayout().getChildren().get(1);
        view.getController().deleteBarang(temp.getItem());
    }
}
