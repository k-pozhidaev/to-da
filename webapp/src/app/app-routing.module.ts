import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {GoalsComponent} from "./page/goals/goals.component";

const routes: Routes = [
  {path: '', redirectTo: '/goals', pathMatch: 'full'},
  {
    path: 'goals',
    component: GoalsComponent,
    data: {
      title: "Goals"
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
