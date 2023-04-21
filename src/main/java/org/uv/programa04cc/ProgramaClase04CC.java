/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.uv.programa04cc;

/**
 *
 * @author zS20006736
 */
public class ProgramaClase04CC {

    public static void main(String[] args) {
        DAOEmpleado dao=new DAOEmpleado();
//        Empleado emp= new Empleado();
//        emp.setClave(2);
//        emp.setNombre("Fermin");
//        emp.setDireccion("Avenida 2");
//        emp.setTelefono("1122334455");
//        dao.create(emp);
//
//        //Delete
//        dao.delete(2);
//        
        //Update
        //Empleado p=new Empleado();
//        p.setNombre("Antonio");
//        p.setDireccion("Calle 2");
//        p.setTelefono("1122334455");
//        dao.update(1, p);
//        
        //FindALl
        dao.findAll();
//        
//        //Find by Id
//        dao.findById(1);
    }
}
