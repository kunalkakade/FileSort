import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

public class FileSort extends JPanel implements ActionListener {
    private static String OS;
    private static String defpath;
    /**
     * for entering a newLine in the log
     */
    static private final String newline;

    static {
        newline = "\n";
    }

    private Label path;
    private JButton Change, Sort;
    private JTextArea log;
    private JFileChooser fc;
    private Set<String> extList ;

    private FileSort(){
        super(new BorderLayout());
        loadmap();
        extList = new HashSet<>();
        OS = System.getProperty("os.name");
        defpath = defaultpath(isWindows());

        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setCurrentDirectory(new File(defpath));

        path = new Label();
        path.setText("Folder to be sort:- "+defpath);


        Change = new JButton("Change");
        Change.addActionListener(this);


        JPanel lablePanel = new JPanel();
        lablePanel.add(path);
        lablePanel.add(Change);

        Sort = new JButton("Sort");
        Sort.addActionListener(this);



        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(lablePanel,BorderLayout.CENTER);
        buttonPanel.add(Sort);
        add(logScrollPane, BorderLayout.CENTER);

        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);

    }

    private void loadmap() {




    }

    private boolean isWindows(){
        return OS.startsWith("Windows");
    }

    private String defaultpath(boolean isWin) {
        if(isWin) {
            return System.getProperty("user.home") + "\\Downloads";
        } else
            return System.getProperty("user.home")+"/Downloads";
    }

    private void listFiles(File root) {
        String[] Images = {".jpeg", ".jpg", ".tiff", ".gif", ".bmp",
                ".png", ".bpg", ".svg", ".heif", ".psd"};
        String[] Videos = {".avi", ".flv", ".wmv", ".mov", ".mp4", ".webm", ".vob",
                ".mng", ".qt", ".mpg", ".mpeg", ".3gp", ".mkv",".MP4"};
        String[] Audios = {".aac", ".aa", ".aac", ".dvf", ".m4a", ".m4b", ".m4p", ".mp3",
                ".msv", "ogg", "oga", ".raw", ".vox", ".wav", ".wma"};
        String[] Archives = {".a", ".ar", ".cpio", ".iso", ".tar", ".gz", ".rz", ".7z",
                ".dmg", ".rar", ".xar", ".zip"};
        String[] Documents = {".oxps", ".epub", ".pages", ".docx", ".doc", ".fdf", ".ods",
                ".odt", ".pwi", ".xsn", ".xps", ".dotx", ".docm", ".dox",
                ".rvg", ".rtf", ".rtfd", ".wpd", ".xls", ".xlsx", ".ppt", ".pptx"};
        String[] PDFs = {".pdf"};
        String[] executible_Programs ={".exe",".msi",".sh"};
        String[] codes = {".java",".cpp",".py",".c"};
        String[] Torrents = {".torrent"};
        File[] ListofFiles  = root.listFiles();
        assert ListofFiles != null;
        log.append(ListofFiles.length +" Files Found"+newline);
        log.append(Arrays.toString(ListofFiles) +newline);
        ArrayList<File> files = new ArrayList<>();
        String name;
        String[] block;
        for (File ListofFile : ListofFiles) {
            if (ListofFile.isFile()) {
                files.add(ListofFile);
                name = ListofFile.getName();
                block = name.split("\\.");
                extList.add(block[block.length - 1]);
            }
        }
        log.append(extList+newline);
        log.append(extList.size()+" extenshion found "+newline);
        String ext;
        for (File e:files) {
            name = e.getName();
            block = name.split("\\.");
            ext ="."+ block[block.length-1];

            if(Arrays.asList(Images).contains(ext.toLowerCase())){
                log.append("File:"+ name +" ---> Images"+newline);
              File images = new File(defpath+"/Images");
                if(!images.isDirectory()){
                    images.mkdir();
                    log.append("Images Directory Created..."+newline);
              }
              e.renameTo(new File(defpath+"/Images/"+ name));
            }
            else if(Arrays.asList(Videos).contains(ext.toLowerCase())){
                log.append("File:"+ name +" ---> Videos"+newline);
                File videos = new File(defpath+"/Videos");
                if(!videos.isDirectory()){
                    videos.mkdir();
                    log.append("Videos Directory Created..."+newline);
                }
                e.renameTo(new File(defpath+"/Videos/"+ name));
            }

            else if(Arrays.asList(executible_Programs).contains(ext.toLowerCase())){
                log.append("File:"+ name +" ---> ThisCanRun"+newline);
                File ThisCanRun = new File(defpath+"/ThisCanRun");
                if(!ThisCanRun.isDirectory()){
                    ThisCanRun.mkdir();
                    log.append("ThisCanRun Directory Created..."+newline);
                }
                e.renameTo(new File(defpath+"/ThisCanRun/"+ name));
            }
            else if(Arrays.asList(Audios).contains(ext.toLowerCase())){
                log.append("File:"+ name +" ---> Audios"+newline);
                File audios = new File(defpath+"/Audios");
                if(!audios.isDirectory()){
                    audios.mkdir();
                    log.append("Audios Directory Created..."+newline);
                }
                e.renameTo(new File(defpath+"/Audios/"+ name));
            }

            else if(Arrays.asList(Archives).contains(ext.toLowerCase())){
                log.append("File:"+ name +" ---> Archives"+newline);
                File archives = new File(defpath+"/Archives");
                if(!archives.isDirectory()){
                    archives.mkdir();
                    log.append("Archives Directory Created..."+newline);
                }
                e.renameTo(new File(defpath+"/Archives/"+ name));
            }

            else if(Arrays.asList(codes).contains(ext.toLowerCase())){
                log.append("File:"+ name +" ---> codes"+newline);
                File code = new File(defpath+"/codes");
                if(!code.isDirectory()){
                    code.mkdir();
                    log.append("codes Directory Created..."+newline);
                }
                e.renameTo(new File(defpath+"/code/"+ name));
            }

            else if(Arrays.asList(PDFs).contains(ext.toLowerCase())){
                log.append("File:"+ name +" ---> PDFs"+newline);
                File pdf = new File(defpath+"/PDFs");
                if(!pdf.isDirectory()){
                    pdf.mkdir();
                    log.append("PDFs Directory Created..."+newline);
                }
                e.renameTo(new File(defpath+"/PDFs/"+ name));
            }


            else if(Arrays.asList(Documents).contains(ext.toLowerCase())){
                log.append("File:"+ name +" ---> Documents"+newline);
                File Document = new File(defpath+"/Documents");
                if(!Document.isDirectory()){
                    Document.mkdir();
                    log.append("Documents Directory Created..."+newline);
                }
                e.renameTo(new File(defpath+"/Documents/"+ name));
            }

            else if(Arrays.asList(Torrents).contains(ext.toLowerCase())){
                log.append("File:"+ name +" ---> Torrents"+newline);
                File Torrent = new File(defpath+"/Torrent");
                if(!Torrent.isDirectory()){
                    Torrent.mkdir();
                    log.append("Torrent Directory Created..."+newline);
                }
                e.renameTo(new File(defpath+"/Torrent/"+ name));
            }
        }


    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //Turn off metal's use of bold fonts
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            createAndShowGUI();
        });
    }
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("FileSorter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new FileSort());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        File file = new File(defpath);
        if (e.getSource() == Change) {
            int returnVal = fc.showOpenDialog(FileSort.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                 file = fc.getSelectedFile();
                //This is where a real application would open the file.
                defpath = file.getAbsolutePath();
                path.setText("Folder to be sort:- "+defpath);
                log.append("Folder to be sorted changed : " + file.getAbsolutePath() + "." + newline);
            } else {
                log.append("Change command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

            //Handle save button action.
        } else if (e.getSource() == Sort) {
            listFiles(file);

            }
            log.setCaretPosition(log.getDocument().getLength());

    }
}
