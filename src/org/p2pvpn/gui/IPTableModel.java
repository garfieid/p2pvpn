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

package org.p2pvpn.gui;

import java.util.Date;
import java.util.Vector;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import org.p2pvpn.network.ConnectionManager;
import org.p2pvpn.network.ConnectorListener;
import org.p2pvpn.network.PeerID;
import org.p2pvpn.network.Connector;
import org.p2pvpn.network.RoutungTableListener;

/**
 *
 * @author wolfgang
 */
public class IPTableModel implements ConnectorListener, TableModel {

	/*
	 * Table Columns: IP, ID, Added, Info, Keep
	 */
	
	private ConnectionManager connectionManager;
	private Connector connector;
	private Vector<TableModelListener> listeners;
	private Connector.Endpoint[] table;
	
	public IPTableModel(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
		connector = connectionManager.getConnector();
		connector.addListener(this);
		listeners = new Vector<TableModelListener>();
		table = connector.getIPs();
	}
	
	@Override
	public void ipListChanged(Connector connector) {
		table = connector.getIPs();
		for(TableModelListener l : listeners) {
			l.tableChanged(new TableModelEvent(this));
		}
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);
	}

	@Override
	public Class<?> getColumnClass(int c) {
		switch (c) {
		case 0: return String.class; 
		case 1: return String.class; 
		case 2: return String.class; 
		case 3: return String.class; 
		case 4: return Boolean.class; 
		default: return null;
		}
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public String getColumnName(int c) {
		switch (c) {
		case 0: return "IP"; 
		case 1: return "ID"; 
		case 2: return "Added"; 
		case 3: return "Info"; 
		case 4: return "Keep"; 
		default: return null;
		}
	}

	@Override
	public int getRowCount() {
		return table.length;
	}

	@Override
	public Object getValueAt(int r, int c) {
		switch (c) {
		case 0: return table[r];
		case 1: return connector.getIpInfo(table[r]).getPeerID();
		case 2: return new Date(connector.getIpInfo(table[r]).getTimeAdded());
		case 3: return connector.getIpInfo(table[r]).getLastInfo();
		case 4: return connector.getIpInfo(table[r]).isKeepForEver();
		default: return null;
		}
	}

	@Override
	public boolean isCellEditable(int r, int c) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);		
	}

	@Override
	public void setValueAt(Object o, int r, int c) {
	}
}