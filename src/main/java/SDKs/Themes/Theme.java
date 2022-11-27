package SDKs.Themes;

import java.awt.*;

public abstract class Theme {
    private String name, directory;
    private Image preview;

    public Theme(String name, String directory, Image preview) {
        this.name = name;
        this.directory = directory;
        this.preview = preview;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public Image getPreview() {
        return preview;
    }

    public void setPreview(Image preview) {
        this.preview = preview;
    }

    public void init() {
    }
}
