package org.posapp.model.datastore;

import java.io.File;
import java.util.ArrayList;

public interface AdapterData<T> {
    public ArrayList<T> read(File file) throws Exception;
    public void write(ArrayList<T> list, File file) throws Exception;
}