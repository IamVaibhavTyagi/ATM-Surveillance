/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GUI;

import static org.bytedeco.javacpp.opencv_core.FONT_HERSHEY_PLAIN;
import static org.bytedeco.javacpp.opencv_highgui.waitKey;
import static org.bytedeco.javacpp.opencv_imgproc.CV_BGR2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.circle;
import static org.bytedeco.javacpp.opencv_imgproc.cvtColor;
import static org.bytedeco.javacpp.opencv_imgproc.goodFeaturesToTrack;
import static org.bytedeco.javacpp.opencv_imgproc.line;
import static org.bytedeco.javacpp.opencv_imgproc.putText;
import static org.bytedeco.javacpp.opencv_video.calcOpticalFlowPyrLK;
//import com.beans.UserModel;
import com.DB.ConnectionManager;
import com.constants.ServerConstants;
import com.helper.OpenCVHelper;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
//import org.bytedeco.javacpp.opencv_core.FONT_HERSHEY_PLAIN;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JOptionPane;

import org.bytedeco.javacpp.indexer.UByteIndexer;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.MatVector;
import org.bytedeco.javacpp.opencv_core.Moments;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_core.Scalar;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;
import org.bytedeco.javacpp.opencv_video.BackgroundSubtractorMOG2;
import org.bytedeco.javacpp.opencv_videoio;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
//import org.bytedeco.javacv.android.recognize.technowings.ServerConstants;
//import org.bytedeco.javacv.android.recognize.technowings.CommonFunctions;
//import org.bytedeco.javacv.android.recognize.technowings.ServerConstants;
//import org.bytedeco.javacv.android.recognize.technowings.VideoWriter;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_video.*;

/**
 *
 * @author technowings
 */
public class FaceAuthentication extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    private CascadeClassifier faceDetector = new CascadeClassifier("haarcascade_frontalface_alt.xml");
    private RectVector detectedFaces = new RectVector();
    opencv_videoio.VideoCapture cap = null;
    boolean breakLoop = false;

    public FaceAuthentication() {
        initComponents();
        jButton1.setVisible(false);
        jLabel3.setVisible(false);
        new SwingWorker<Object, Object>() {

            @Override
            protected Object doInBackground() throws java.lang.Exception {
                cap = new opencv_videoio.VideoCapture(0);
                opencv_core.Mat captured = new opencv_core.Mat();
                if (captured != null) {
                    cap.read(captured);
                    System.out.println("Width/Height " + captured.cols() + "/" + captured.rows());
                    captured.release();
                }
                Thread.sleep(5000);
                while (!breakLoop) {
                    captured = new opencv_core.Mat();
                    cap.read(captured);
                    if (captured != null) {
                        String todaysDate = ServerConstants.videoDisplayDateFormat.format(new Date());

                        putText(captured, todaysDate, new opencv_core.Point(20, 50), FONT_HERSHEY_DUPLEX, 1.3,
                                new opencv_core.Scalar(0, 0, 255, 0));

                        // resize(captured, captured1, );
                        opencv_core.Mat grayimage = new opencv_core.Mat();
                        cvtColor(captured, grayimage, CV_BGR2GRAY);
                        // Perform background Task start
                        // detect the faces
                        // faceDetector.detectMultiScale(captured, detectedFaces);
                        faceDetector.detectMultiScale(grayimage, detectedFaces);
                        System.out.println(" detectedFaces.size() " + detectedFaces.size());
                        long size = detectFaceInImage(detectedFaces);
                        for (int i = 0; i < detectedFaces.size(); i++) {
                            Rect object = detectedFaces.get(i);
                            System.out.println("object  " + object.width() + " " + object.height());
                            rectangle(captured, new org.bytedeco.javacpp.opencv_core.Point(object.x(), object.y()),
                                    new org.bytedeco.javacpp.opencv_core.Point(object.x() + object.width(),
                                            object.y() + object.height()),
                                    Scalar.GREEN, 1, 8, 0);
                            // face = new Mat(captured, object);
                            // facecount = (int) detectedFaces.size();
                            // rect = new Rect(object.x(),object.area(),object.width(),object.height());

                        }
                        Frame capt = com.helper.OpenCVHelper.mat2frame(captured);
                        BufferedImage bicap = com.helper.OpenCVHelper.frame2buffered(capt);
                        resultFrame.setIcon(new ImageIcon(bicap));
                        if (size == 1) {

                            jLabel3.setVisible(true);
                            imwrite("stored/valid/Valid_" + System.currentTimeMillis() + ".jpg", captured);
                            jButton1.setVisible(true);
                        } else if (size > 1) {
                            JOptionPane.showMessageDialog(new Login(),
                                    "There can not be more than 2 faces in front of atm");
                            jLabel3.setVisible(true);
                            imwrite("stored/invalid/InValid_" + System.currentTimeMillis() + ".jpg", captured);
                            jButton1.setVisible(false);
                        } else {
                            jButton1.setVisible(false);
                            JOptionPane.showMessageDialog(new Login(),
                                    "Face is not clearly visible try again by taking off helmet or scarf");

                        }
                        captured.release();

                    }
                }
                return null;
            }

            private long detectFaceInImage(RectVector detectedFaces) {
                long size = 0;
                size = detectedFaces.size();
                return size;
            }
        }.execute();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        resultFrame = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Smart Surveillance System For ATM Centers");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout
                        .createSequentialGroup().addContainerGap().addComponent(jLabel4,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(28, Short.MAX_VALUE)));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Start Transaction "));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("16 Digit Atm No");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("4 Digit Pin");

        jTextField1.setBackground(new java.awt.Color(255, 255, 204));

        jTextField2.setBackground(new java.awt.Color(255, 255, 204));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Start Transaction");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("Do not Move head while Processing...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup().addGap(26, 26, 26).addGroup(jPanel1Layout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 296,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 121,
                                                        Short.MAX_VALUE)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 155,
                                                        Short.MAX_VALUE)
                                                .addComponent(jTextField2))))
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 242,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(30, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jLabel1))
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 39,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2).addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28).addComponent(jLabel3).addGap(41, 41, 41).addComponent(jButton1,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96)));

        resultFrame.setBorder(
                javax.swing.BorderFactory.createTitledBorder("Live Camera - Smile You are Under Surveiliance"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup().addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resultFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 640,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(resultFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 480,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        // HashMap parameter = new HashMap();
        String uname = jTextField1.getText();
        String password = jTextField2.getText();
        // parameter.put("uname", jTextField1.getText());
        // parameter.put("password", jTextField2.getText());
        // JOptionPane.showMessageDialog(new Login(), uname + pass);
        System.out.println("username " + uname + "password " + password);
        // UserModel um = (UserModel) ConnectionManager.checkLogin(uname, password);
        // if (um == null) {
        // JOptionPane.showMessageDialog(new FaceAuthentication(), "Please enter valid
        // credintials");
        // } else {
        // this.setVisible(false);
        // new ProcessLiveCamera().setVisible(true);
        // }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FaceAuthentication.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FaceAuthentication.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FaceAuthentication.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FaceAuthentication.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FaceAuthentication().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel resultFrame;
    // End of variables declaration
}
