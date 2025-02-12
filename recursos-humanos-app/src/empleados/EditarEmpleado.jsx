import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";


export const EditarEmpleado = () => {
  const urlBase ='http://localhost:8081/rh-app/empleado';
  
  const navegacion = useNavigate();
  
  const {id} = useParams();

  const [empleado,setEmpleado]= useState({
    nombre:"",
    departamento:"",
    sueldo:""
  });

  const {nombre,departamento,sueldo} = empleado;

  useEffect(()=>{
    cargarEmpleado();
  },[])

  const cargarEmpleado = async () =>{
    const resultado = await axios.get(`${urlBase}/${id}`)
    setEmpleado(resultado.data);
  }
  const onInputChange = (e) =>{
    //spread operador ...(Expandir los atributos)
    setEmpleado({...empleado,[e.target.name]: e.target.value});
  }

  const onSubmit = async (e) =>{
    e.preventDefault();
    
    await axios.put(`${urlBase}/${}`,empleado);
    navegacion('/');

  }

  return (
    <div className="container">
      <div className="container text-center" style={{margin:"30px"}}> 
        <h3>Editar Empleado</h3>
      </div>
                  <form  onSubmit={(e)=>onSubmit(e)}>
              <div className="mb-3">
                <label  htmlFor="nombre" className="form-label">Nombre:</label>
                <input type="text" className="form-control" name="nombre" id="nombre" required={true}
                value={nombre} onChange={(e)=>onInputChange(e)}
                />
            
              </div>
              <div className="mb-3">
                <label  htmlFor="departamento" className="form-label">Departamento:</label>
                <input type="text" className="form-control" name="departamento" id="departamento"
                value={departamento} onChange={(e)=>onInputChange(e)}/>
            
              </div>
              <div className="mb-3">
                <label  htmlFor="sueldo" className="form-label">Sueldo:</label>
                <input type="number"  step="any" className="form-control" name="sueldo" id="sueldo"
                value={sueldo} onChange={(e)=>onInputChange(e)}
                />
            
              </div>
              <div className="text-center">
              <button type="submit" className="btn btn-warning me-3 btn-sm">Guardar</button>
              <a href="/" className="btn btn-danger btn-sm">Regresar</a>
              </div>
              
            </form>

    </div>
    
  
  
  )
}
