package testreporter.core.models;

import javax.persistence.*;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;

@Table(name = "Result_image")
@Entity
public class ResultFile {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

    @Column(name = "result_file_data")
    @Lob
    private byte[] data;

    @Column(name = "result_file_name")
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
