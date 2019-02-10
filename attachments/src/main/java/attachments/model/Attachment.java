package attachments.model;

import javax.persistence.*;

@Table(name = "tr_attachment")
@Entity
public class Attachment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "tr_attachment_data")
    @Lob
    private byte[] data;

    @Column(name = "tr_attachment_name")
    private String attachmentName;

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
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

