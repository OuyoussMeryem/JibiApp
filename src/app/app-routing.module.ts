import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
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
import { AgenceListComponent } from './components/admin/agence-list/agence-list.component';
import { AddAgenceComponent } from './components/admin/add-agence/add-agence.component';
import { ChangepasswordclientComponent } from './components/client/changepasswordclient/changepasswordclient.component';
import { CreatePaymentAccountComponent } from './components/client/create-payment-account/create-payment-account.component';
import { ProfileComponent } from './components/client/profile/profile.component';
import { InvoiceHistoryComponent } from './components/client/invoice-history/invoice-history.component';
import { AgencesComponent } from './components/client/agences/agences.component';
import { FacturesnonpayeComponent } from './components/client/facturesnonpaye/facturesnonpaye.component';
import { PayeFormComponent } from './components/client/paye-form/paye-form.component';
import { ConfirmepayeComponent } from './components/client/confirmepaye/confirmepaye.component';

const routes: Routes = [
  { path: '', component: AboutComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'login', component: LoginComponent},
  { path: 'agent', component: AgentComponent },
  { path: 'client', component: ClientComponent},
  { path: 'change-password', component: ChangepasswordComponent },
  { path: 'app-changepasswordclient', component: ChangepasswordclientComponent},
  { path: 'add-client', component: AddClientComponent },
  { path: 'clients', component: ClientListComponent },
  { path: 'add-agent', component: AddAgentComponent },
  { path: 'agents', component: AgentListComponent },
  { path: 'add-agence', component: AddAgenceComponent},
  { path: 'agences', component: AgenceListComponent },
  { path: 'app-create-payment-account', component: CreatePaymentAccountComponent },
  { path: 'invoice-history', component: InvoiceHistoryComponent },
 { path: 'crianciers', component:AgencesComponent  },
  { path: 'profil', component: ProfileComponent },
  { path: 'facturesnonpaye', component: FacturesnonpayeComponent },
  { path: 'paye-form', component: PayeFormComponent },
  { path: 'confirmepaye', component: ConfirmepayeComponent },
  // { path: 'depot', component: DepotComponent },
  // { path: 'payer-factures', component: PayerFacturesComponent },
  // { path: 'agence', component: AgenceComponent },
   
  // { path: 'clients', component: ClientsComponent },
  // { path: 'demande', component: DemandeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
