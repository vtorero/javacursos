import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UsuariosListaComponent } from './usuarios-lista/usuarios-lista.component';
import { HttpClientModule } from '@angular/common/http';
import { AgregarUsuariioComponent } from './agregar-usuario/agregar-usuario.component';
import { FormsModule } from '@angular/forms';
import { EditarUsuarioComponent } from './editar-usuario/editar-usuario.component';

@NgModule({
  declarations: [
    AppComponent,
    UsuariosListaComponent,
    AgregarUsuariioComponent,
    EditarUsuarioComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
