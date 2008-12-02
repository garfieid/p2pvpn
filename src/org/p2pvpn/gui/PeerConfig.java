/*
    Copyright 2008 Wolfgang Ginolas

    This file is part of P2PVPN.

    P2PVPN is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Foobar is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

/*
 * PeerConfig.java
 *
 * Created on 17. November 2008, 11:02
 */

package org.p2pvpn.gui;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.p2pvpn.tuntap.TunTap;
import org.p2pvpn.network.ConnectionManager;
import org.p2pvpn.network.VPNConnector;
import org.p2pvpn.tools.AdvProperties;

/**
 *
 * @author  wolfgang
 */
public class PeerConfig extends javax.swing.JFrame {

	static private Random random = new Random();
	
    /** Creates new form PeerConfig */
    public PeerConfig() {
        initComponents();
		
		txtNetwork.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent arg0) {
				changedUpdate(arg0);
			}

			public void removeUpdate(DocumentEvent arg0) {
				changedUpdate(arg0);
			}

			public void changedUpdate(DocumentEvent arg0) {
				txtNetworkChanged();
			}
		});
    }

	private void txtNetworkChanged() {
		try {
			AdvProperties netCfg = new AdvProperties(txtNetwork.getText());
			InetAddress net = InetAddress.getByName(netCfg.getProperty("ip.network"));
			InetAddress subnet = InetAddress.getByName(netCfg.getProperty("ip.subnet"));

			byte[] myIPb = new byte[4];
			byte[] netb = net.getAddress();
			byte[] subnetb = subnet.getAddress();

			// TODO don't create a broadcast address
			for (int i = 0; i < 4; i++) {
				myIPb[i] = (byte)(netb[i] ^ ((~subnetb[i]) & (byte)random.nextInt()));
			}

			txtPeerIP.setText((0xFF&myIPb[0])+"."+(0xFF&myIPb[1])+"."+(0xFF&myIPb[2])+"."+(0xFF&myIPb[3]));
		} catch (UnknownHostException ex) {
		}
	}
	
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCreateNet = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNetwork = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPeerName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPeerIP = new javax.swing.JTextField();
        chkVPN = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        spnLocalPort = new javax.swing.JSpinner();
        btnCancel = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Peer Configuration");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Network"));

        btnCreateNet.setText("Create a Network...");
        btnCreateNet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateNetActionPerformed(evt);
            }
        });

        txtNetwork.setColumns(20);
        txtNetwork.setRows(5);
        jScrollPane1.setViewportView(txtNetwork);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addComponent(btnCreateNet))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCreateNet)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Peer"));

        jLabel1.setText("Name");

        jLabel2.setText("VPN IP-Address");

        chkVPN.setSelected(true);
        chkVPN.setText("Create VPN");
        chkVPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkVPNActionPerformed(evt);
            }
        });

        jLabel3.setText("Local Port");

        spnLocalPort.setModel(new javax.swing.SpinnerNumberModel(0, 0, 65535, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPeerIP, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                            .addComponent(txtPeerName, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                            .addComponent(spnLocalPort, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)))
                    .addComponent(chkVPN))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPeerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPeerIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(spnLocalPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkVPN)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnOK))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void chkVPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkVPNActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_chkVPNActionPerformed

private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
	System.exit(0);
}//GEN-LAST:event_btnCancelActionPerformed

private void btnCreateNetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateNetActionPerformed
	NetworkConfig nc = new NetworkConfig(this, true);
	
	nc.setVisible(true);
	AdvProperties settings = nc.getSettings();
	
	if (settings!=null) {
		txtNetwork.setText(nc.getSettings().toString(null, true));
	}
}//GEN-LAST:event_btnCreateNetActionPerformed

private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
// TODO add your handling code here:
try {
        AdvProperties netCfg = new AdvProperties(txtNetwork.getText());
		
		if (!netCfg.containsKey("ip.subnet")) {
	        JOptionPane.showMessageDialog(null, "Please copy an invitation into the Network field", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

        ConnectionManager cm = new ConnectionManager((Integer) spnLocalPort.getModel().getValue());

        setVisible(false);

		if (chkVPN.isSelected()) {
            cm.getRouter().setLocalPeerInfo("vpn.ip", txtPeerIP.getText());
            TunTap tunTap = TunTap.createTunTap();
            tunTap.setIP(txtPeerIP.getText(), netCfg.getProperty("ip.subnet"));
            new VPNConnector(cm, tunTap, cm.getRouter());
        }
        cm.getRouter().setLocalPeerInfo("name", txtPeerName.getText());
        cm.getConnector().addIPs(netCfg);
        org.p2pvpn.gui.Main.open(cm);
    } catch (Throwable e) {
		Logger.getLogger("").log(Level.SEVERE, "", e);
        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }
}//GEN-LAST:event_btnOKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreateNet;
    private javax.swing.JButton btnOK;
    private javax.swing.JCheckBox chkVPN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spnLocalPort;
    private javax.swing.JTextArea txtNetwork;
    private javax.swing.JTextField txtPeerIP;
    private javax.swing.JTextField txtPeerName;
    // End of variables declaration//GEN-END:variables

}
