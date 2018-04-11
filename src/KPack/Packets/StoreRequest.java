package KPack.Packets;

import KPack.Files.KadFileInterf;
import KPack.KadNode;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StoreRequest implements Serializable {

    private BigInteger fileID;
    private String fileName;
    private KadNode source;
    private KadNode dest;
    private byte[] content;

    public StoreRequest(KadFileInterf kf, KadNode source, KadNode dest)
    {
        this.fileID = kf.getFileID();
        this.fileName = kf.getFileName();
        this.source = source;
        this.dest = dest;
        try
        {
            //Orribile ma temporanea
            if(!((new File(kf.getPath())).isDirectory())) throw new IOException("Errore nella composizione del KadFile, nel path");
            if((new File(kf.getPath()+File.separator+kf.getFileName())).isDirectory()) throw new IOException("Errore nella composizione del KadFile, il file è una directory");
            Path path = Paths.get(kf.getPath()+ File.separator+kf.getFileName());
            content = Files.readAllBytes(path);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public KadNode getSourceKadNode()
    {
        return source;
    }

    public KadNode getDestKadNode()
    {
        return dest;
    }

    public BigInteger getFileID()
    {
        return fileID;
    }

    public String getFileName()
    {
        return fileName;
    }

    public byte[] getContent()
    {
        return content;
    }
}
