import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {GoalsComponent} from "./page/goals/goals.component";
import {CreateGoalComponent} from "./page/create-goal/create-goal.component";
import {AchievementsComponent} from "./page/achievements/achievements.component";

const routes: Routes = [
  {path: '', redirectTo: '/goals', pathMatch: 'full'},
  {
    path: 'goals',
    component: GoalsComponent,
    data: {
      title: "Goals"
    }
  },
  {
    path: 'edit-goal',
    component: CreateGoalComponent,
    data: {
      title: "Create Goal"
    }
  },
  {
    path: 'achievements',
    component: AchievementsComponent,
    data: {
      title: 'Achievements'
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
