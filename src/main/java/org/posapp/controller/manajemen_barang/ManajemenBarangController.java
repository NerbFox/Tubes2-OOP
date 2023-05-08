package org.posapp.controller.manajemen_barang;

import javafx.collections.FXCollections;
import lombok.Getter;
import lombok.Setter;
import org.posapp.model.Barang;
import org.posapp.model.datastore.Datastore;
import org.posapp.view.ManajemenBarang.DetailBarangView;
import org.posapp.view.ManajemenBarang.ManajemenBarangView;

@Getter @Setter
public class ManajemenBarangController {
    private ManajemenBarangView view;
    private Datastore model;
    public static Integer greatestID;

    public ManajemenBarangController(ManajemenBarangView _view) {
        view = _view;
        model = Datastore.getInstance();
    }

    public void addBarang(){
        view.setRightSideLayout(new DetailBarangView (new Barang(-1, "", "", 0, (float) 0, (float) 0, "file:./src/main/resources/image/50.png"), view));
        view.getLayout().getChildren().set(1, view.getRightSideLayout());
        //        view.getLayout().getChildren().set(1, new DetailBarangView(new Barang(-1, "", "", 0, 0, 0, ""), view));
    }

    public void updateBarang(Barang item, String nama, String kategori, String stok, String hargaJual, String hargaBeli, String pathGambar) throws InvalidInputException {
        if (item.getIdBarang() != -1) {
            model.getArrBarang().remove(item);

        }
        else {
            greatestID++;
            item.setIdBarang(greatestID);
        }
        item.setNama(nama);
        item.setKategori(kategori);
        try {
            item.setStok(Integer.parseInt(stok));
            item.setHargaJual(org.posapp.controller.currency.CurrencyWrapper.getInstance().getConvertedCurrencyBack(Float.parseFloat(hargaJual)));
            item.setHargaBeli(org.posapp.controller.currency.CurrencyWrapper.getInstance().getConvertedCurrencyBack(Float.parseFloat(hargaBeli)));
            item.setPathGambar(pathGambar);
            model.getArrBarang().add(item);
            view.getTable().setItems(FXCollections.observableArrayList(model.getArrBarang()));
        } catch (Exception err) {
            throw new InvalidInputException("Format Input Harga Jual atau Harga Beli Tidak Valid.");
        }
        System.out.println("ID Barang : " + item.getIdBarang());
    }

    public void deleteBarang(Barang item){
        model.getArrBarang().remove(item);
        view.getTable().setItems(FXCollections.observableArrayList(model.getArrBarang()));
    }

    public static Integer getGreatIDFormDS(){
        int id = 0;
        for (Barang barang : Datastore.getInstance().getArrBarang()){
            if (id < barang.getIdBarang()){
                id = barang.getIdBarang();
            }
        }
        return id;
    }
}
