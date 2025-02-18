import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsuariosListaComponent } from './usuarios-lista/usuarios-lista.component';
import { AgregarUsuariioComponent } from './agregar-usuario/agregar-usuario.component';
import { EditarUsuarioComponent } from './editar-usuario/editar-usuario.component';

//http://localhost:4200/usuarios
const routes: Routes = [
  {path:'usuarios',component:UsuariosListaComponent},
  {path:'',redirectTo:'usuarios',pathMatch:'full'},
  {path:'agregar-usuario',component:AgregarUsuariioComponent},
  {path:'editar-usuario/:id',component:EditarUsuarioComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
