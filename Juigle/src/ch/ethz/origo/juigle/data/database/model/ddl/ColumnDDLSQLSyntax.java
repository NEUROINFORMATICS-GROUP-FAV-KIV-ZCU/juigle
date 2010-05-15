/*
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/*
 *  
 *    Copyright (C) 2009 - 2010 
 *    							University of West Bohemia, 
 *                  Department of Computer Science and Engineering, 
 *                  Pilsen, Czech Republic
 */
package ch.ethz.origo.juigle.data.database.model.ddl;

import ch.ethz.origo.juigle.application.exception.database.SQLDDLException;
import ch.ethz.origo.juigle.data.database.model.DBColumn;
import ch.ethz.origo.juigle.data.database.model.DBComponent;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/1/2010)
 * @since 0.1.0 (3/1/2010)
 * @see AbstractDDLSQLSyntax
 *
 */
public class ColumnDDLSQLSyntax extends AbstractDDLSQLSyntax {
	
	private DBColumn column;

	public ColumnDDLSQLSyntax(DBComponent component) {
		super(component);
		this.column = (DBColumn) component;
	}

	@Override
	public String add() throws SQLDDLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String create() throws SQLDDLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String drop() throws SQLDDLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modify() throws SQLDDLException {
		// TODO Auto-generated method stub
		return null;
	}

}
