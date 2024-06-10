import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field'; // Importez MatFormFieldModule
import { MatInputModule } from '@angular/material/input'; // Importez MatInputModule
import { MatSelectModule } from '@angular/material/select'; 
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AboutComponent } from './components/about/about.component';
import { AdminComponent } from './components/admin/admin/admin.component';
import { LoginComponent } from './components/login/login.component';
import { AgentComponent } from './components/agent/agent/agent.component';
import { ClientComponent } from './components/client/client/client.component';
import { ChangepasswordComponent } from './components/agent/changepassword/changepassword.component';
import { AddClientComponent } from './components/agent/add-client/add-client.component';
import { ClientListComponent } from './components/agent/client-list/client-list.component';
import { AddAgentComponent } from './components/admin/add-agent/add-agent.component';
import { AgentListComponent } from './components/admin/agent-list/agent-list.component';
import { AddAgenceComponent } from './components/admin/add-agence/add-agence.component';
import { AgenceListComponent } from './components/admin/agence-list/agence-list.component';
import { ChangepasswordclientComponent } from './components/client/changepasswordclient/changepasswordclient.component';
import { CreatePaymentAccountComponent } from './components/client/create-payment-account/create-payment-account.component';
import { ProfileComponent } from './components/client/profile/profile.component';
import { InvoiceHistoryComponent } from './components/client/invoice-history/invoice-history.component';
import { AgencesComponent } from './components/client/agences/agences.component';
import { FacturesnonpayeComponent } from './components/client/facturesnonpaye/facturesnonpaye.component';
import { PayeFormComponent } from './components/client/paye-form/paye-form.component';
import { ConfirmepayeComponent } from './components/client/confirmepaye/confirmepaye.component';



@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    AboutComponent,
    AdminComponent,
    LoginComponent,
    AgentComponent,
    ClientComponent,
    ChangepasswordComponent,
    AddClientComponent,
    ClientListComponent,
    AddAgentComponent,
    AgentListComponent,
    AddAgenceComponent,
    AgenceListComponent,
    ChangepasswordclientComponent,
    CreatePaymentAccountComponent,
    ProfileComponent,
    InvoiceHistoryComponent,
    AgencesComponent,
    FacturesnonpayeComponent,
    PayeFormComponent,
    ConfirmepayeComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    FormsModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatSelectModule,
    HttpClientModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
