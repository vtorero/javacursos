import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { use } from 'react';
import { NumberFormatBase, NumericFormat } from 'react-number-format';




export default function ListadoEmpleados() {
  const urlBase ='http://localhost:8081/rh-app/empleados';
  const [empleados,setempleados] = useState([]);

  useEffect(()=>{
    cargarEmpleados();
  },[]);

  const cargarEmpleados = async () => {
    const result = await axios.get(urlBase);
    console.log(result.data);
    setempleados(result.data);
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
  {
    // iteramos el arreglo de emplados
    empleados.map((empleado,indice)=>(
      <tr key={indice}>
      <th scope="row">{empleado.idEmpleado}</th>
      <td>{empleado.nombre}</td>
      <td>{empleado.departamento}</td>
      <td>
        <NumericFormat value={empleado.sueldo}
        displayType={'text'}
        thousandSeparator=',' prefix='$'
        decimalScale={2} fixedDecimalScale/>
        </td>
    </tr>
    ))
}

  </tbody>
</table>
    </div>
  )
}

