package api.model.models;

import javax.persistence.*;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;

@Table(name = "file_file")
@Entity
public class File {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "file_file_data")
    @Lob
    private byte[] data;

    @Column(name = "file_file_name")
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
