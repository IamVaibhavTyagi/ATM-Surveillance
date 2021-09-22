/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import com.DB.DBUtils;
import com.GUI.*;
import com.constants.ServerConstants;
import com.googlecode.javacv.cpp.opencv_core;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;
import swinghelper.image.operations.ImageHelper;
import com.googlecode.javacpp.Loader;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import com.helper.AudioPlayer;
import com.helper.SMSSender;
import com.helper.StringHelper;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueMoonLookAndFeel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Random;
import javax.swing.*;
import org.apache.commons.net.DatabaseHelper;
import util.polygon.Point;
import util.polygon.Polygon;

public class Surveillance2 extends javax.swing.JFrame {

    boolean breakLoop = false, startFlag = false;
    public static CvCapture capture = null;
    SwingWorker sw = null;
    public static BufferedImage buffimg = null;

    public Surveillance2() {
        try {
            UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
        } catch (Exception e) {
            // e.printStackTrace();
        }
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        new DatabaseHelper();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        process = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        liveCamera = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        background = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Surveillance Screen");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Process", 0, 0,
                new java.awt.Font("Copperplate Gothic Light", 0, 12))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(process,
                        javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(process,
                        javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Live Camera", 0, 0,
                new java.awt.Font("Copperplate Gothic Light", 0, 12))); // NOI18N
        jPanel4.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 12)); // NOI18N

        liveCamera.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        liveCamera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                liveCameraMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(liveCamera,
                        javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE));
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(liveCamera,
                        javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BackGround", 0, 0,
                new java.awt.Font("Copperplate Gothic Light", 0, 12))); // NOI18N
        jPanel6.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 12)); // NOI18N

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        background.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        background.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                backgroundMousePressed(evt);
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backgroundMouseClicked(evt);
            }
        });
        background.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                backgroundMouseMoved(evt);
            }
        });
        background.setBounds(10, 20, 350, 280);
        jLayeredPane1.add(background, javax.swing.JLayeredPane.POPUP_LAYER);

        jLabel1.setBackground(new java.awt.Color(255, 255, 204));
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel1MouseDragged(evt);
            }
        });
        jLabel1.setBounds(70, 210, 190, 70);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DRAG_LAYER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup().addContainerGap().addComponent(jLayeredPane1,
                        javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)));
        jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup().addContainerGap()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                        .addContainerGap()));

        jLabel20.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Trajectory Detection System");

        jLabel21.setForeground(new java.awt.Color(255, 51, 51));
        jLabel21.setText("Version 0.1");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                        jPanel12Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(jLabel21))
                .addGroup(jPanel12Layout.createSequentialGroup().addGap(25, 25, 25).addComponent(jLabel20)
                        .addContainerGap()));
        jPanel12Layout.setVerticalGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup().addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel20)
                        .addContainerGap(23, Short.MAX_VALUE)));

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2" }));

        jLabel2.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 12)); // NOI18N
        jLabel2.setText("Camera");

        jLabel3.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 12)); // NOI18N
        jLabel3.setText("Resolution");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "640x480", "600x800", "1280x768" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 11)); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnStart.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 11)); // NOI18N
        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnStop.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 11)); // NOI18N
        btnStop.setText("Stop");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 11)); // NOI18N
        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Save Video");

        jCheckBox2.setText("Play Alarm");

        jCheckBox3.setText("Send Notification");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 12)); // NOI18N
        jLabel4.setText("time");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10 Sec", "20 Sec", "30 Sec" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout
                .setHorizontalGroup(
                        jPanel5Layout
                                .createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup().addContainerGap()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 93,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel5Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jCheckBox2, javax.swing.GroupLayout.Alignment.TRAILING,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 83,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(
                                                        jCheckBox1, javax.swing.GroupLayout.Alignment.TRAILING,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 83,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(45, 45, 45)
                                        .addGroup(jPanel5Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jComboBox3,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jCheckBox3, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(90, 90, 90)
                                        .addGroup(jPanel5Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnStart, javax.swing.GroupLayout.DEFAULT_SIZE, 86,
                                                        Short.MAX_VALUE))
                                        .addGap(31, 31, 31)
                                        .addGroup(jPanel5Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnStop, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(52, 52, 52)));
        jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup().addGroup(jPanel5Layout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addContainerGap(12, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1).addComponent(jButton4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnStop).addComponent(btnStart).addComponent(jCheckBox1)
                                        .addComponent(jCheckBox3)))
                        .addGroup(jPanel5Layout.createSequentialGroup().addGap(25, 25, 25).addGroup(jPanel5Layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel5Layout.createSequentialGroup().addContainerGap()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jCheckBox2)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap()));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap()));
        jPanel1Layout
                .setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup().addGap(52, 52, 52)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup().addGap(30, 30, 30).addComponent(
                                                jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>

    BufferedImage img = null;

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {
        startCamera(1);
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int type, Integer img_width,
            Integer img_height) {
        BufferedImage resizedImage = new BufferedImage(img_width, img_height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, img_width, img_height, null);
        g.dispose();

        return resizedImage;
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        try {
            // scale image on disk

            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            BufferedImage resizeImageJpg = resizeImage(originalImage, type, width, height);
            return resizeImageJpg;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void startCamera(int cameraId) {
        String wt = jComboBox2.getSelectedItem().toString();
        // ServerConstants.IMG_WIDTH = StringHelper.n2i(wt.substring(0,
        // wt.indexOf("x")));
        // ServerConstants.IMG_HEIGHT = StringHelper.n2i(wt.substring(wt.indexOf("x") +
        // 1));
        System.out.println("wt===>" + wt);
        ServerConstants.CAMERA_ID = cameraId;
        System.out.println("Camera  ===> " + cameraId);
        breakLoop = false;
        capture = cvCreateCameraCapture(cameraId);
        startFlag = false;
        btnStart.setEnabled(false);
        // processImg = IplImage.create(640, 480, IPL_DEPTH_32F, 4);
        // processImg = cvLoadImage("template.png");
        ServerConstants.IMG_WIDTH = StringHelper.n2i(wt.substring(0, wt.indexOf("x")));
        ServerConstants.IMG_HEIGHT = StringHelper.n2i(wt.substring(wt.indexOf("x") + 1));
        sw = new SwingWorker() {

            IplImage frame = null;

            @Override
            protected Object doInBackground() {
                try {
                    while (!breakLoop) {
                        cvSetCaptureProperty(capture, CV_CAP_PROP_FRAME_WIDTH, ServerConstants.IMG_WIDTH);
                        cvSetCaptureProperty(capture, CV_CAP_PROP_FRAME_HEIGHT, ServerConstants.IMG_HEIGHT);
                        if (capture != null) {
                            frame = cvRetrieveFrame(capture);
                            if (frame != null) {
                                buffimg = frame.getBufferedImage();
                                // IplImage diff = IplImage.create(image.width(), image.height(), frame.depth(),
                                // frame.nChannels());
                                // cvResize(frame, diff);
                                // buffimg = resizeImage(buffimg, 320, 300);

                                liveCamera.setIcon(new ImageIcon(buffimg));
                                liveCamera.setBounds(0, 0, buffimg.getWidth(), buffimg.getHeight());
                                background.setBounds(0, 0, buffimg.getWidth(), buffimg.getHeight());
                                processImage(IplImage.createFrom(buffimg));
                                if (jCheckBox1.isSelected()) {
                                    createVideo(frame);
                                }
                            }
                        }
                        if (ServerConstants.FRAME_VISIBILITY != isVisible()) {
                            setVisible(ServerConstants.FRAME_VISIBILITY);
                        }
                    }
                } catch (Exception e) {
                    try {
                        if (frame != null) {
                            try {
                                frame.release();
                                frame = null;
                            } catch (Exception ec) {
                            }
                        }
                        if (capture != null) {
                            cvReleaseCapture(capture);
                            capture = null;
                        }
                        e.printStackTrace();
                    } catch (Exception ex) {
                        System.out.println("Release error handled.");
                        ex.printStackTrace();
                    }
                } finally {
                    return null;
                }
            }

            @Override
            protected void done() {
                cvReleaseCapture(capture);
                if (writer != null) {
                    cvReleaseVideoWriter(writer);
                }
            }
        };
        sw.execute();

    }

    // int count = 0;
    IplImage image = null, prevImage = null, diff = null, processImg = null;
    CvMemStorage storage = CvMemStorage.create();
    CvPoint prev_center = null;

    public void findCotour(IplImage image) {
        IplImage grayImage = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
        cvCvtColor(image, grayImage, CV_BGR2GRAY);
        if (prevImage == null) {
            prevImage = IplImage.create(image.width(), image.height(), IPL_DEPTH_8U, 1);
            prevImage = grayImage;
        }
        if (diff == null) {
            diff = IplImage.create(image.width(), image.height(), IPL_DEPTH_8U, 1);
        }
        cvAbsDiff(grayImage, prevImage, diff);
        // IplImage image = cvLoadImage(sourcePath);

        CvMemStorage mem;
        CvSeq contours = new CvSeq();
        CvSeq ptr = new CvSeq();
        cvThreshold(diff, diff, 50, 255, CV_THRESH_BINARY);
        mem = cvCreateMemStorage(0);

        cvFindContours(diff, mem, contours, Loader.sizeof(CvContour.class), CV_RETR_CCOMP, CV_CHAIN_APPROX_SIMPLE,
                cvPoint(0, 0));
        if (!contours.isNull()) {
            Random rand = new Random();
            for (ptr = contours; ptr != null; ptr = ptr.h_next()) {
                Color randomColor = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
                CvScalar color = CV_RGB(randomColor.getRed(), randomColor.getGreen(), randomColor.getBlue());

                cvDrawContours(image, ptr, color, CV_RGB(0, 0, 0), -1, CV_FILLED, 8, cvPoint(0, 0));

            }
        }
        background.setIcon(new ImageIcon(image.getBufferedImage()));
        // cvSaveImage(targetPath, image);

    }

    public void processImage(IplImage frame) {
        if (processImg == null) {
            processImg = frame;
        }
        // count++;
        cvSmooth(frame, frame, CV_GAUSSIAN, 9, 9, 2, 2);
        if (frame != null) {
            image = IplImage.create(frame.width(), frame.height(), IPL_DEPTH_8U, 1);
            cvCvtColor(frame, image, CV_RGB2GRAY);
            // prevImage = IplImage.create(frame.width(), frame.height(), IPL_DEPTH_8U, 1);
            // prevImage = image;
            // }
        }
        // else {
        //// if (count % 22 == 0) {
        // prevImage = IplImage.create(frame.width(), frame.height(), IPL_DEPTH_8U, 1);
        // prevImage = image;
        //// }
        // image = IplImage.create(frame.width(), frame.height(), IPL_DEPTH_8U, 1);
        // cvCvtColor(frame, image, CV_RGB2GRAY);
        // }

        // if (diff == null) {
        diff = IplImage.create(frame.width(), frame.height(), IPL_DEPTH_8U, 1);
        // }

        if (prevImage != null) {

            System.out.println("template " + prevImage.width() + " " + prevImage.height());
            System.out.println("image " + image.width() + " " + image.height());
            // perform ABS difference
            // System.out.println("here1");
            // opencv_video.BackgroundSubtractorMOG2 mog2 = new
            // opencv_video.BackgroundSubtractorMOG2();
            cvAbsDiff(image, prevImage, diff);
            // mog2.apply(image, prevImage, 1);

            // mog2.getBackgroundImage(prevImage);
            // do some threshold for wipe away useless details
            cvThreshold(diff, diff, 50, 255, CV_THRESH_BINARY);
            // cvThreshold(image, diff, 100, 255, CV_THRESH_BINARY);
            // System.out.println("here2");
            // canvasFrame.showImage(diff);
            process.setIcon(new ImageIcon(diff.getBufferedImage()));
            // recognize contours

        }
        prevImage = image;
        opencv_core.CvSeq contour = new opencv_core.CvSeq(null);
        cvFindContours(diff, storage, contour, Loader.sizeof(opencv_core.CvContour.class), CV_RETR_LIST,
                CV_CHAIN_APPROX_SIMPLE);

        while (contour != null && !contour.isNull()) {
            if (contour.elem_size() > 0) {
                opencv_core.CvBox2D box = cvMinAreaRect2(contour, storage);

                // test intersection
                if (box != null) {// && box.size().height() > 20 && box.size().width() > 20

                    opencv_core.CvPoint2D32f center = box.center();
                    opencv_core.CvSize2D32f size = box.size();

                    // System.out.println(" New Center <==>" +center.x()+" "+center.y()+ " <==> W/H
                    // " + box.size().width() + " " + box.size().height());
                    if (center.x() > 0 && center.y() > 0 && center.x() < 320 && center.y() < 280
                            && box.size().height() > 5 && box.size().width() > 5) {
                        if (prev_center == null) {
                            prev_center = cvPoint((int) center.x(), (int) center.y());
                            System.out.println(" Setting <==>" + (int) prev_center.x() + " " + (int) prev_center.y()
                                    + " <==> Current  " + (int) center.x() + " " + (int) center.y() + "W?H "
                                    + box.size().width() + " " + box.size().height());
                        }
                        if (prev_center.x() > 0 && prev_center.y() > 0 && center.x() > 0 && center.y() > 0
                                && prev_center.x() < 320 && prev_center.y() < 280) {
                            System.out.println(" Previous <==>" + (int) prev_center.x() + " " + (int) prev_center.y()
                                    + " <==> Current  " + (int) center.x() + " " + (int) center.y() + "W?H "
                                    + box.size().width() + " " + box.size().height());
                            cvDrawLine(processImg, prev_center, cvPoint((int) center.x(), (int) center.y()),
                                    CvScalar.RED, 1, 1, 0);
                            prev_center = cvPoint((int) center.x(), (int) center.y());
                            background.setIcon(new ImageIcon(processImg.getBufferedImage()));
                            checkBOX(prev_center);
                            break;

                        } else {

                            prev_center = cvPoint((int) center.x(), (int) center.y());
                            System.out.println(" Setting2 <==>" + (int) prev_center.x() + " " + (int) prev_center.y()
                                    + " <==> Current  " + (int) center.x() + " " + (int) center.y() + "W?H "
                                    + box.size().width() + " " + box.size().height());
                        }

                    }
                }
            }
            contour = contour.h_next();
        }
    }

    public void checkBOX(CvPoint point) {
        int x = jLabel1.getX();
        int y = jLabel1.getY();
        int wid = jLabel1.getWidth();
        int hei = jLabel1.getHeight();
        System.out.println(" x y w h " + x + " " + y + " " + wid + " " + hei);

        Polygon polygon = Polygon.Builder().addVertex(new Point(1, 2)).addVertex(new Point(x, y))
                .addVertex(new Point(x + wid, y)).addVertex(new Point(x + wid, y + hei))
                .addVertex(new Point(x, y + hei)).build();
        boolean b = polygon.contains(new Point(point.x(), point.y()));
        if (b) {

            SMSSender s = new SMSSender(ServerConstants.ADMIN_PHONE,
                    "Sombody is doing suscpicious activity in ATM. PLease check the video.  ");
            startTime = System.currentTimeMillis();
            try {
                // aid, description, atime
                String query = "insert into auditlog (description) values (?);";
                DBUtils.executeUpdate(query, "TRAJECTORY DETECTED");
                s.send();
                playBuzzer();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Point");
            // JOptionPane.showMessageDialog(this, "Point");
        }
    }

    CvVideoWriter writer = null;
    long startTime = 0;

    public static IplImage writeFrame(IplImage frame) {
        String text = "Video:" + StringHelper.formatdate(new Date());
        CvPoint v = new CvPoint();
        v.x(frame.width() - 250);
        v.y(100);
        CvArr c = frame;
        CvFont font = new CvFont();
        double hScale = 0.1;
        double vScale = 0.1;
        int lineWidth = 1;
        cvInitFont(font, CV_FONT_NORMAL, 1.0, hScale, vScale, 0, lineWidth);
        cvPutText(c, text, v, font, CvScalar.RED);
        return (IplImage) c;
    }

    public void createVideo(IplImage frame) {
        if (writer == null) {
            CvSize cs = new CvSize();
            int isColor = 1;
            int fps = 30; // or 30
            cs.height(ServerConstants.IMG_HEIGHT);
            cs.width(ServerConstants.IMG_WIDTH);
            String path = ServerConstants.VIDEOSTREAMING_FOLDER_NAME + System.currentTimeMillis() + ".avi";
            writer = cvCreateVideoWriter(path, CV_FOURCC('F', 'L', 'V', '1'), fps, cs, isColor);
        }
        System.out.println("writing");
        cvWriteFrame(writer, writeFrame(frame));
    }

    public void notifyAdmin() {
        if (jCheckBox2.isSelected()) {
            Thread playAlarm = new Thread() {

                long curTime = System.currentTimeMillis();
                long interval = (jComboBox3.getSelectedIndex() + 1) * 10 * 1000;

                public void run() {
                    while (curTime - startTime < interval) {
                        try {
                            playBuzzer();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            };
            playAlarm.start();
        }
    }

    public void playBuzzer() throws Exception {
        String audioFilePath = ServerConstants.EMERGENCY_ALERT;
        if (jCheckBox2.isSelected()) {
            player.load(audioFilePath);
            player.play();
        }

    }

    public static AudioPlayer player = new AudioPlayer();

    public void setImage() {

        SwingWorker swingworker = new SwingWorker() {

            @Override
            protected Object doInBackground() {
                while (!breakLoop) {
                    ServerConstants.zoom.jLabel1.setIcon(new ImageIcon(buffimg));
                }
                return null;
            }
        };
        swingworker.execute();
    }

    private void liveCameraMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        ServerConstants.zoom = new ZoomImage();
        ServerConstants.zoom.setVisible(true);
        setImage();
    }

    BufferedImage template = null;
    IplImage templateIMG = null;

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (buffimg != null) {
            try {
                ImageIO.write(buffimg, "png", new File("template.png"));
                template = ImageHelper.deepCopy(buffimg);
                templateIMG = IplImage.create(template.getWidth(), template.getHeight(), IPL_DEPTH_8U, 1);
                cvCvtColor(IplImage.createFrom(template), templateIMG, CV_RGB2GRAY);
                background.setIcon(new ImageIcon(template));
                background.setBounds(0, 0, buffimg.getWidth(), buffimg.getHeight());
            } catch (IOException ex) {
                Logger.getLogger(Surveillance2.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        breakLoop = true;
        btnStart.setEnabled(true);
    }

    int dragX = 1, dragY = 1, dragW = 1, dragH = 1, dx = 1, dy = 1;

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    boolean draw = true;

    private void backgroundMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:

        draw = !draw;
        pressed = true;
        dragX = evt.getX();
        dragY = evt.getY();
        System.out.println("draw " + draw);
        // if (SwingUtilities.isLeftMouseButton(evt) && !start) {
        // jLabel1.setEnabled(false);
        // background.repaint();
        // System.out.println("in start point");
        // dragX = evt.getX();
        // dragY = evt.getY();
        // dx = dragX < evt.getX() ? dragX : evt.getX();
        // dy = dragY < evt.getY() ? dragY : evt.getY();
        // start = true;
        // } else if (SwingUtilities.isLeftMouseButton(evt) && start) {
        // System.out.println("in end point");
        // jLabel1.setVisible(true);
        // if (evt.getX() < background.getWidth()) {
        // dragW = Math.abs(evt.getX() - dragX);
        // }
        //
        // if (evt.getY() < background.getHeight()) {
        // dragH = Math.abs(evt.getY() - dragY);
        // }
        //
        //// dx = dragX < evt.getX() ? dragX : evt.getX();
        //// dy = dragY < evt.getY() ? dragY : evt.getY();
        // System.out.println("X Y W H => " + dx + " " + dy + " " + dragW + " " +
        // dragH);
        // jLabel1.setBounds(dx, dy, dragW, dragH);
        // jLabel1.setBackground(new Color(.5f, 0, 0, .2f));
        //
        // jLabel1.setOpaque(true);
        // jLabel1.setEnabled(true);
        // start = false;
        // } else if (SwingUtilities.isRightMouseButton(evt)) {
        // System.out.println("in clear point");
        // jLabel1.setEnabled(false);
        // background.repaint();
        // }
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        jLabel1.setBounds(100, 100, 200, 200);
        jLabel1.setBackground(new Color(.5f, 0, 0, .2f));

        jLabel1.setOpaque(true);
        jLabel1.setEnabled(true);
    }

    boolean pressed = false;

    private void backgroundMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        pressed = true;
        dragX = evt.getX();
        dragY = evt.getY();
        dx = dragX < evt.getX() ? dragX : evt.getX();
        dy = dragY < evt.getY() ? dragY : evt.getY();
    }

    private void backgroundMouseMoved(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        if (draw && pressed) {
            jLabel1.setVisible(true);
            if (evt.getX() < background.getWidth()) {
                dragW = Math.abs(evt.getX() - dragX);
            }

            if (evt.getY() < background.getHeight()) {
                dragH = Math.abs(evt.getY() - dragY);
            }

            dx = dragX < evt.getX() ? dragX : evt.getX();
            dy = dragY < evt.getY() ? dragY : evt.getY();
            System.out.println("X Y W H => " + dx + " " + dy + " " + dragW + " " + dragH);
            jLabel1.setBounds(dx, dy, dragW, dragH);
            jLabel1.setBackground(new Color(.5f, 0, 0, .2f));

            jLabel1.setOpaque(true);
            jLabel1.setEnabled(true);
        }
    }

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        draw = !draw;
        pressed = true;
        System.out.println("draw " + draw);
        dragX = evt.getX() + jLabel1.getX();
        dragY = evt.getY() + jLabel1.getY();
        dx = dragX;
        dy = dragY;

    }

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
                    Surveillance2 s = new Surveillance2();
                    s.setExtendedState(s.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                    s.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel background;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel liveCamera;
    private javax.swing.JLabel process;
    // End of variables declaration
}
