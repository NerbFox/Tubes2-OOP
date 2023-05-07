package org.posapp.controller.command;

import org.posapp.controller.command.Command;
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
