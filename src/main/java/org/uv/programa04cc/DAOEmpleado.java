/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.programa04cc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zS20006736
 */
public class DAOEmpleado implements IDAOGeneral<Empleado, Integer> {

    @Override
    public Empleado create(Empleado p) {
        ConexionDB cx = ConexionDB.getInstance();
        TransactionDB tbd = new TransactionDB<Empleado>(p) {
            @Override
            public boolean execute(Connection con) {
                PreparedStatement psm = null;  // Declarar PreparedStatement fuera del try-catch
                try {
                    String sql = "insert into empleado(clave, nombre, direccion, telefono)"
                            + "values (?,?,?,?)";
                    psm = con.prepareStatement(sql);
                    psm.setInt(1, p.getClave());
                    psm.setString(2, p.getNombre());
                    psm.setString(3, p.getDireccion());
                    psm.setString(4, p.getTelefono());
                    psm.execute();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                } finally {
                    // Cerrar PreparedStatement y Connection dentro del bloque finally
                    if (psm != null) {
                        try {
                            psm.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        cx.execute(tbd);
        return p;
    }

    @Override
    public boolean delete(Integer id) {
        ConexionDB cx = ConexionDB.getInstance();
        TransactionDB tbd;
        tbd = new TransactionDB<Integer>(id) {
            @Override
            public boolean execute(Connection con) {
                PreparedStatement psm = null;  // Declarar PreparedStatement fuera del try-catch
                try {
                    String sql = "delete from empleado where clave=?";
                    psm = con.prepareStatement(sql);
                    psm.setInt(1, id);
                    psm.execute();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                } finally {
                    if (psm != null) {
                        try {
                            psm.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        };

        boolean resp = cx.execute(tbd);
        if (resp) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, "Se ha eliminado");
            return true;
        } else {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, "Error");
            return false;
        }
    }

    @Override
    public Empleado update(Integer id, Empleado p) {
        ConexionDB cx = ConexionDB.getInstance();
        TransactionDB tbd = new TransactionDB<Empleado>(p) {
            @Override
            public boolean execute(Connection con) {
                PreparedStatement psm = null;  // Declarar PreparedStatement fuera del try-catch
                try {
                    String sql = "update empleado set nombre=?, direccion=?, telefono=? where clave=?";
                    psm = con.prepareStatement(sql);
                    psm.setString(1, p.getNombre());
                    psm.setString(2, p.getDireccion());
                    psm.setString(3, p.getTelefono());
                    psm.setInt(4, id);
                    psm.execute();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                } finally {
                    // Cerrar PreparedStatement y Connection dentro del bloque finally
                    if (psm != null) {
                        try {
                            psm.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        cx.execute(tbd);
        return p;
    }

    @Override
    public List<Empleado> findAll() {
        List<Empleado> empleados = new ArrayList<>();
        ConexionDB cx = ConexionDB.getInstance();
        TransactionDB tbd = new TransactionDB<List<Empleado>>(empleados) {
            @Override
            public boolean execute(Connection con) {
                PreparedStatement psm = null;
                try {
                    String sql = "select * from empleado";
                    psm = con.prepareStatement(sql);
                    psm.execute();
                    if (empleados != null) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                } finally {
                    // Cerrar PreparedStatement y Connection dentro del bloque finally
                    if (psm != null) {
                        try {
                            psm.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        boolean resp = cx.execute(tbd);
        if (resp) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Lista empleados");
            return empleados;
        } else {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Lista vacia");
            return null;
        }
    }

    @Override
    public Empleado findById(Integer id) {
        ConexionDB cx = ConexionDB.getInstance();
        Empleado p = new Empleado();
        TransactionDB tbd = new TransactionDB<Empleado>(p) {
            @Override
            public boolean execute(Connection con) {
                PreparedStatement psm = null;
                try {
                    String sql = "select * from empleado where clave=?";
                    psm = con.prepareStatement(sql);
                    psm.setInt(1, id);
                    psm.execute();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                } finally {

                    if (psm != null) {
                        try {
                            psm.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            }
        };

        boolean resp = cx.execute(tbd);
        if (resp) {
            return p;
        } else {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, "Empleado no encontrado");
            return null;
        }
    }

}
