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

const routes: Routes = [
  { path: '', component: AboutComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'login', component: LoginComponent},
  { path: 'agent', component: AgentComponent },
  { path: 'client', component: ClientComponent},
  { path: 'change-password', component: ChangepasswordComponent },
  { path: 'add-client', component: AddClientComponent },
  { path: 'clients', component: ClientListComponent },
  { path: 'add-agent', component: AddAgentComponent },
  { path: 'agent', component: AgentListComponent },
  // { path: 'profil', component: ProfilComponent },
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
