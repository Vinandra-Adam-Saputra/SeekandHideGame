import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.JSlider;


public class HideAndSeek extends JPanel implements ActionListener {

    private final int SIZE = 40;
    private final int ROWS = 15;
    private final int COLS = 15; 
    private static final int WALL = 1;
    private static final int MAP_ROWS = 15;
    private static final int MAP_COLS = 15;
    private int[][] maze;
    private Droid redDroid;
    private Droid greenDroid;
    private Timer timer;
    private Image backgroundImage;
    private List<Droid> droids;
    private Random random = new Random();
    private int visionRadius = 1;
    private JSlider slider;
    private JLabel labelJarakPandang;
    private boolean gameOver;
    private boolean isGreenDroidVisible = true;
    private boolean isGreenVisionVisible = false;

    //Deklarasi Tombol
    private JButton TombolMulai;
    private JButton TombolBerhenti;
    private JButton TombolAcakMap;
    private JButton TombolAcakDroidMerah;
    private JButton TombolAcakDroidHijau;
    private JButton TombolBermainKembali;
    private JButton TombolKeluar;
    private JButton TombolTambahDroidMerah;
    private JButton TombolKurangDroidMerah;
    private JButton TombolPandanganDroidMerah;
    private JButton TombolPandanganDroidHijau;

    
    public HideAndSeek() {
        setPreferredSize(new Dimension(SIZE * COLS + 180, SIZE * ROWS));
        backgroundImage = new ImageIcon("Background.png").getImage();
        maze = generateMaze();
        
        redDroid = new Droid(0, 0, Color.RED);
        greenDroid = new Droid(ROWS - 1, COLS - 1, Color.GREEN);
         droids = new ArrayList<>();
         droids.add(redDroid);

        timer = new Timer(700, this);

        TombolMulai = new JButton("Mulai");
        TombolMulai.setFont(new Font("Consolas", Font.BOLD, 15)); // Mengatur ukuran font
        TombolMulai.setBackground(Color.BLACK); // Menambahkan warna latar belakang merah pada tombol
        TombolMulai.setForeground(Color.WHITE);
        TombolMulai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });

        TombolBerhenti = new JButton("Berhenti");
        TombolBerhenti.setFont(new Font("Consolas", Font.BOLD, 15)); 
        TombolBerhenti.setBackground(Color.BLACK); 
        TombolBerhenti.setForeground(Color.WHITE);
        TombolBerhenti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });

        TombolAcakMap = new JButton("Acak Map");
        TombolAcakMap.setFont(new Font("Consolas", Font.BOLD, 15)); 
        TombolAcakMap.setBackground(Color.BLACK); 
        TombolAcakMap.setForeground(Color.WHITE);
        TombolAcakMap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                maze = generateMaze();
                repaint();
            }
        });

        TombolAcakDroidMerah = new JButton("Acak Droid Merah");
        TombolAcakDroidMerah.setFont(new Font("Consolas", Font.BOLD, 15)); 
        TombolAcakDroidMerah.setBackground(Color.BLACK); 
        TombolAcakDroidMerah.setForeground(Color.WHITE);
        TombolAcakDroidMerah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               for (Droid droid : droids) {
                droid.shufflePosition();
                 repaint();
            }

               
            }
        });

        TombolAcakDroidHijau = new JButton("Acak Droid Hijau");
        TombolAcakDroidHijau.setFont(new Font("Consolas", Font.BOLD, 15)); 
        TombolAcakDroidHijau.setBackground(Color.BLACK); 
        TombolAcakDroidHijau.setForeground(Color.WHITE);
        TombolAcakDroidHijau.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                greenDroid.shufflePosition();
                repaint();
            }
        });

        TombolBermainKembali = new JButton("Bermain Kembali");
        TombolBermainKembali.setFont(new Font("Consolas", Font.BOLD, 15)); 
        TombolBermainKembali.setBackground(Color.WHITE); 
        TombolBermainKembali.setForeground(Color.BLACK);
        TombolBermainKembali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        TombolKeluar = new JButton("Keluar Permainan");
        TombolKeluar.setFont(new Font("Consolas", Font.BOLD, 15)); 
        TombolKeluar.setBackground(Color.WHITE); 
        TombolKeluar.setForeground(Color.BLACK);
        TombolKeluar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

       TombolTambahDroidMerah = new JButton("Tambah Droid Merah");
        TombolTambahDroidMerah.setFont(new Font("Consolas", Font.BOLD, 15)); 
        TombolTambahDroidMerah.setBackground(Color.BLACK); 
        TombolTambahDroidMerah.setForeground(Color.WHITE);
        TombolTambahDroidMerah.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
             tambahDroidMerah();
    }
});

        TombolKurangDroidMerah = new JButton("Kurangi Droid Merah");
        TombolKurangDroidMerah.setFont(new Font("Consolas", Font.BOLD, 14));
        TombolKurangDroidMerah.setBackground(Color.BLACK); 
        TombolKurangDroidMerah.setForeground(Color.WHITE);
        TombolKurangDroidMerah.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
             kurangDroidMerah();
    }
});

        TombolPandanganDroidMerah = new JButton("Pandangan Droid Merah");
        TombolPandanganDroidMerah.setFont(new Font("Consolas", Font.BOLD, 14)); 
        TombolPandanganDroidMerah.setBackground(Color.BLACK); 
        TombolPandanganDroidMerah.setForeground(Color.WHITE);
        TombolPandanganDroidMerah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            toggleDroidMerahVisibility();
    }
});

        TombolPandanganDroidHijau = new JButton("Pandangan Droid Hijau");
        TombolPandanganDroidHijau.setFont(new Font("Consolas", Font.BOLD, 14)); 
        TombolPandanganDroidHijau.setBackground(Color.BLACK); 
        TombolPandanganDroidHijau.setForeground(Color.WHITE);
        TombolPandanganDroidHijau.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            isGreenVisionVisible = !isGreenVisionVisible;
            repaint();
    }
});


        slider = new JSlider(1, 5, visionRadius);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBounds(SIZE * COLS + 500, 580, 210, 50);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            visionRadius = slider.getValue();
            repaint();
    }
});

        labelJarakPandang = new JLabel(" Jarak Pandang Droid Hijau ");
        labelJarakPandang.setFont(new Font("Consolas", Font.BOLD, 13));
        labelJarakPandang.setForeground(Color.BLACK);

        //Mengatur Posisi Tombol
        TombolMulai.setBounds(SIZE * COLS + 500, 190, 210, 30);
        TombolBerhenti.setBounds(SIZE * COLS + 500, 230, 210, 30);
        TombolAcakMap.setBounds(SIZE * COLS + 500, 270, 210, 30);
        TombolAcakDroidMerah.setBounds(SIZE * COLS + 500, 310, 210, 30);
        TombolAcakDroidHijau.setBounds(SIZE * COLS + 500, 350, 210, 30);
        TombolTambahDroidMerah.setBounds(SIZE * COLS + 500, 390, 210, 30);
        TombolKurangDroidMerah.setBounds(SIZE * COLS + 500, 430, 210, 30);
        TombolPandanganDroidMerah.setBounds(SIZE * COLS + 500, 470, 210, 30);
        TombolPandanganDroidHijau.setBounds(SIZE * COLS + 500, 510, 210, 30);
        labelJarakPandang.setBounds(SIZE * COLS + 500, 550, 210, 30);



        setLayout(null);
        add(TombolMulai);
        add(TombolBerhenti);
        add(TombolAcakMap);
        add(TombolAcakDroidMerah);
        add(TombolAcakDroidHijau);
        add(TombolTambahDroidMerah);
        add(TombolKurangDroidMerah);
        add(TombolPandanganDroidMerah);
        add(TombolPandanganDroidHijau);
        add(slider);
        add(labelJarakPandang);

        gameOver = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(),getHeight(), null);

    //Menggambar Map
    for (int row = 0; row < ROWS; row++) {
    for (int col = 0; col < COLS; col++) {
        int x = col * SIZE + 450;
        int y = row * SIZE + 110;

        if (maze[row][col] == WALL || (isGreenDroidVisible && isGreenVisionVisible)) {
            g.setColor(Color.BLACK);
            g.fillRect(x, y, SIZE, SIZE);
        } else {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, SIZE, SIZE);
        }
        g.setColor(Color.BLACK);
        g.drawRect(x, y, SIZE, SIZE);
    }
}
      
    for (int row = Math.max(greenDroid.getRow() - visionRadius, 0); row <= Math.min(greenDroid.getRow() + visionRadius, ROWS - 1); row++) {
    for (int col = Math.max(greenDroid.getCol() - visionRadius, 0); col <= Math.min(greenDroid.getCol() + visionRadius, COLS - 1); col++) {
        int x = col * SIZE + 450;
        int y = row * SIZE + 110;

        if (maze[row][col] == WALL) {
            g.setColor(Color.BLACK);
            g.fillRect(x, y, SIZE, SIZE);
        } else if (!isGreenVisionVisible) {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, SIZE, SIZE);
        } else {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, SIZE, SIZE);
        }
        g.setColor(Color.BLACK);
        g.drawRect(x, y, SIZE, SIZE);
    }
}
         // Menggambar droid merah baru
        for (Droid droid : droids) {
        int droidX = droid.getCol() * SIZE + 450;
        int droidY = droid.getRow() * SIZE + 110;
        ImageIcon redDroidIcon = new ImageIcon("Red.png");
            g.drawImage(redDroidIcon.getImage(), droidX, droidY, null);
    }
        // Menggambar droid merah
        int redX = redDroid.getCol() * SIZE + 450;
        int redY = redDroid.getRow() * SIZE + 110;
        ImageIcon redDroidIcon = new ImageIcon("Red.png");
            g.drawImage(redDroidIcon.getImage(), redX, redY, null);

        
        // Menggambar droid hijau
         int greenDroidX = greenDroid.getCol() * SIZE + 450;
         int greenDroidY = greenDroid.getRow() * SIZE + 110;

if (maze[greenDroid.getRow()][greenDroid.getCol()] != WALL && isGreenDroidVisible) {
    if (isGreenVisionVisible || (greenDroid.getRow() >= Math.max(greenDroid.getRow() - visionRadius, 0) && greenDroid.getRow() <= Math.min(greenDroid.getRow() + visionRadius, ROWS - 1) && greenDroid.getCol() >= Math.max(greenDroid.getCol() - visionRadius, 0) && greenDroid.getCol() <= Math.min(greenDroid.getCol() + visionRadius, COLS - 1))) {
        g.setClip(greenDroidX, greenDroidY, SIZE, SIZE);
        ImageIcon greenDroidIcon = new ImageIcon("Green.png");
        g.drawImage(greenDroidIcon.getImage(), greenDroidX, greenDroidY, null);
        g.setClip(null);
    } else {
        g.fillRect(greenDroidX, greenDroidY, SIZE, SIZE);
    }
}
        if (gameOver) {
            Font font = new Font("Calisto MT", Font.BOLD, 50);
            FontMetrics fontMetrics = g.getFontMetrics(font);
            int messageWidth = fontMetrics.stringWidth("Game Over");
            int messageHeight = fontMetrics.getHeight();
            int centerX = SIZE * COLS / 2 - messageWidth / 2 + 450;
            int centerY = SIZE * ROWS / 2 - messageHeight / 2 + 110;
            g.setColor(Color.RED);
            g.setFont(font);
            g.drawString("Game Over", centerX, centerY);
            TombolBermainKembali.setBounds(SIZE * COLS + 50, centerY + messageHeight + -30, 200, 50);
            TombolKeluar.setBounds(SIZE * COLS + 50, centerY + messageHeight + 30, 200, 50);
            add(TombolBermainKembali);
            add(TombolKeluar);
        } else {
            remove(TombolBermainKembali);
            remove(TombolKeluar);
        }
    }

    private int[][] generateMaze() {
        int[][] maze = new int[ROWS][COLS];
        // Inisialisasi semua sel sebagai tembok
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                maze[row][col] = 1;
            }
        }  
        generateMazeRecursive(maze, 0, 0);  
        // Tandai titik masuk dan keluar sebagai jalan
        maze[0][0] = 0; // Titik masuk
        maze[ROWS - 1][COLS - 1] = 0; // Titik keluar 
        return maze;
    }
    
    private void generateMazeRecursive(int[][] maze, int row, int col) {
        maze[row][col] = 0; // Tandai posisi saat ini sebagai jalan
        // Buat daftar urutan acak untuk empat arah
        List<String> directions = Arrays.asList("UP", "DOWN", "LEFT", "RIGHT");
        Collections.shuffle(directions); 
        // Untuk setiap arah dalam urutan acak
        for (String direction : directions) {
            int newRow = row;
            int newCol = col;
            // Perbarui posisi sesuai arah
            if (direction.equals("UP") && row > 1) {
                newRow -= 2;
            } else if (direction.equals("DOWN") && row < ROWS - 2) {
                newRow += 2;
            } else if (direction.equals("LEFT") && col > 1) {
                newCol -= 2;
            } else if (direction.equals("RIGHT") && col < COLS - 2) {
                newCol += 2;
            }  
            // Periksa apakah sel yang diarahkan valid
            if (isValidCell(maze, newRow, newCol)) {
                // Hapus tembok di antara posisi saat ini dan posisi baru
                maze[(row + newRow) / 2][(col + newCol) / 2] = 0;
    
                // Rekursif memanggil generateMaze() untuk posisi baru
                generateMazeRecursive(maze, newRow, newCol);
            }
        }
    }
    
    private boolean isValidCell(int[][] maze, int row, int col) {
        // Periksa apakah sel berada dalam batas maze dan belum dikunjungi
        return row >= 0 && row < ROWS && col >= 0 && col < COLS && maze[row][col] == 1;
    }

    private void tambahDroidMerah() {
     Random random = new Random();

    // Iterasi hingga menemukan posisi jalan yang valid
        int newRow, newCol;
    do {
        newRow = random.nextInt(ROWS);
        newCol = random.nextInt(COLS);
    } while (maze[newRow][newCol] == WALL);

    if (droids.size() >= 5) {
        JOptionPane.showMessageDialog(this, "Batas maksimum jumlah droid merah telah tercapai!");
        return;
    }

    Droid newRedDroid = new Droid(newRow, newCol, Color.RED);
    droids.add(newRedDroid);
    repaint();
}

    private void kurangDroidMerah() {
        if (droids.size() > 1) {
            Droid lastDroid = droids.get(droids.size() - 1);
            if (lastDroid != redDroid) {
                droids.remove(lastDroid);
                repaint();
            } else {
                JOptionPane.showMessageDialog(this, " ");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Batas minimum jumlah droid merah telah tercapai!");
        }
        
    }

   private void toggleDroidMerahVisibility() {
    isGreenDroidVisible = !isGreenDroidVisible;
    repaint();
}

    private void moveDroids() {
        if (!gameOver) {
          for (Droid droid : droids) {
          droid.move();
          greenDroid.move();
          isVisible();
}
           
}
           if ((redDroid.getRow() == greenDroid.getRow() && redDroid.getCol() == greenDroid.getCol())) {
                gameOver = true;
                timer.stop();
            }
            repaint();
        }

    private void resetGame() {
        gameOver = false;
        redDroid.shufflePosition();
        greenDroid.shufflePosition();
        timer.start();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveDroids();
    }

    private class Droid {
        private int row;
        private int col;
        private Color color;
        private int x;
        private boolean isVisible = true;
        private int lastMove = -1;

        public Droid(int row, int col, Color color) {
            this.row = row;
            this.col = col;
            this.color = color;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public Color getColor() {
            return color;
        }

    public void toggleVisibility() {
    isVisible = !isVisible;
}

    public boolean isVisible() {
    return isVisible;
}

  //Pergerakan Kedua Droid
    public void move() {
    if (color == Color.RED) {
        if (greenDroid != null) {
            // Mencari rute menggunakan algoritma BFS
            Node targetNode = bfs(greenDroid.getRow(), greenDroid.getCol());

            if (targetNode != null) {
                // Mengikuti rute terpendek menuju droid hijau
                followPath(targetNode);
            } else {
                // Tidak ada rute yang ditemukan, bergerak secara acak
                moveRandomly();
            }
        } else {
            // Droid hijau tidak ditemukan atau tidak terlihat, bergerak secara acak
            moveRandomly();
        }
    } else if (color == Color.GREEN) {
    // Pergerakan Droid Hijau (Menelusuri Map dan Menghindar dari Droid Merah)
    if (redDroid != null) {
        int horizontalDistance = col - redDroid.getCol();
        int verticalDistance = row - redDroid.getRow();

        // Memilih arah pergerakan berdasarkan jarak terdekat dengan droid merah
        if (Math.abs(horizontalDistance) >= Math.abs(verticalDistance)) {
            // Pergerakan horizontal
            if (horizontalDistance > 0 && isValidMove(row, col - 1)) {
                col -= 1; // Bergerak ke kiri
            } else if (horizontalDistance < 0 && isValidMove(row, col + 1)) {
                col += 1; // Bergerak ke kanan
            } else {
                // Tidak ada langkah yang valid, droid hijau bergerak secara acak
                moveRandomly();
            }
        } else {
            // Pergerakan vertikal
            if (verticalDistance > 0 && isValidMove(row - 1, col)) {
                row -= 1; // Bergerak ke atas
            } else if (verticalDistance < 0 && isValidMove(row + 1, col)) {
                row += 1; // Bergerak ke bawah
            } else {
                // Tidak ada langkah yang valid, droid hijau bergerak secara acak
                moveRandomly();
            }
        }
    } else {
        // Droid merah tidak ditemukan atau tidak terlihat, bergerak menelusuri map
        if (lastMove == 0) {
            // Bergerak ke bawah
            if (isValidMove(row + 1, col)) {
                row += 1;
            } else {
                moveRandomly();
            }
        } else if (lastMove == 1) {
            // Bergerak ke kanan
            if (isValidMove(row, col + 1)) {
                col += 1;
            } else {
                moveRandomly();
            }
        } else if (lastMove == 2) {
            // Bergerak ke atas
            if (isValidMove(row - 1, col)) {
                row -= 1;
            } else {
                moveRandomly();
            }
        } else if (lastMove == 3) {
            // Bergerak ke kiri
            if (isValidMove(row, col - 1)) {
                col -= 1;
            } else {
                moveRandomly();
            }
        }
    }
        // Periksa kolisi dengan droid merah
        if (redDroid != null && row == redDroid.getRow() && col == redDroid.getCol()) {
        
        }
    }
}

private Node bfs(int targetRow, int targetCol) {
    boolean[][] visited = new boolean[MAP_ROWS][MAP_COLS];
    // Antrian untuk BFS
    Queue<Node> queue = new LinkedList<>();
    queue.add(new Node(row, col, null));
    visited[row][col] = true;
    // Gerakan yang mungkin (kiri, kanan, atas, bawah)
    int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    while (!queue.isEmpty()) {
        Node currentNode = queue.poll();

        // Jika simpul saat ini adalah target, kembalikan simpul tersebut
        if (currentNode.row == targetRow && currentNode.col == targetCol) {
            return currentNode;
        }

        // Periksa gerakan yang mungkin dari simpul saat ini
        for (int[] direction : directions) {
            int newRow = currentNode.row + direction[0];
            int newCol = currentNode.col + direction[1];

            if (isValidMove(newRow, newCol) && !visited[newRow][newCol]) {
                queue.add(new Node(newRow, newCol, currentNode));
                visited[newRow][newCol] = true;
            }
        }
    }

    // Tidak ada rute yang ditemukan
    return null;
}
    private void followPath(Node targetNode) {
    List<Node> path = new ArrayList<>();

    // Membalik rute dari simpul target ke simpul awal
    Node currentNode = targetNode;
    while (currentNode != null) {
        path.add(currentNode);
        currentNode = currentNode.parent;
    }

    // Mengikuti langkah pertama dalam rute terpendek
    if (path.size() > 1) {
        Node nextNode = path.get(path.size() - 2);
        if (nextNode.row > row) {
            row += 1; // Bergerak ke bawah
            lastMove = 2;
        } else if (nextNode.row < row) {
            row -= 1; // Bergerak ke atas
            lastMove = 0;
        } else if (nextNode.col > col) {
            col += 1; // Bergerak ke kanan
            lastMove = 1;
        } else if (nextNode.col < col) {
            col -= 1; // Bergerak ke kiri
            lastMove = 3;
        }
    } else {
        // Tidak ada langkah yang valid, droid merah bergerak secara acak
        moveRandomly();
    }
}



private class Node {
    int row;
    int col;
    Node parent;

    public Node(int row, int col, Node parent) {
        this.row = row;
        this.col = col;
        this.parent = parent;
    }
}

private void moveRandomly() {
    List<int[]> validMoves = getValidMoves();
    if (!validMoves.isEmpty()) {
        int[] randomMove = validMoves.get(new Random().nextInt(validMoves.size()));
        row = randomMove[0];
        col = randomMove[1];
    }
}

private List<int[]> getValidMoves() {
    List<int[]> validMoves = new ArrayList<>();

    // Memeriksa setiap langkah yang mungkin (kiri, kanan, atas, bawah)
    int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    for (int[] direction : directions) {
        int newRow = row + direction[0];
        int newCol = col + direction[1];

        if (isValidMove(newRow, newCol)) {
            int[] move = {newRow, newCol};
            validMoves.add(move);
        }
    }

    return validMoves;
}
        public void shufflePosition() {
            int newRow, newCol;
            do {
                newRow = (int) (Math.random() * ROWS);
                newCol = (int) (Math.random() * COLS);
            } while (!isValidMove(newRow, newCol));

            row = newRow;
            col = newCol;
        }

        private boolean isValidMove(int newRow, int newCol) {
            return newRow >= 0 && newRow < ROWS && newCol >= 0 && newCol < COLS && maze[newRow][newCol] != 1;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hide and Seek Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new HideAndSeek());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}