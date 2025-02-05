import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { use } from 'react';




export default function ListadoEmpleados() {
  const urlBase ='http://localhost:8081/rh-app/empleados';
  const [empleados,setempleados] = useState([]);
  
  useEffect(()=>{
    cargarEmpleados();
  },[]);
  
  const cargarEmpleados = async () => {
    const result = await axios.get(urlBase);
    console.log(result.data);
  };

  return (
    <div className='container'>
    <div className='container text-center' style={{margin:"30px"}}>
    <h3>Sistema de recursos humanos</h3>
    </div>
    <table className="table table-striped table-hover">
  <thead className='table-dark'>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Empleado</th>
      <th scope="col">Departamento</th>
      <th scope="col">Sueldo</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Mark2</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
   
    
  </tbody>
</table>
    </div>
  )
}

