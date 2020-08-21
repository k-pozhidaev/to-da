import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToolbarComponent } from './components/toolbar/toolbar.component';
import { GoalsComponent } from './page/goals/goals.component';
import { MatButtonModule } from '@angular/material/button';
import {MAT_CHIPS_DEFAULT_OPTIONS, MatChipsModule} from '@angular/material/chips';
import { NavigationComponent } from './components/navigation/navigation.component';
import { CreateGoalComponent } from './page/create-goal/create-goal.component';
import { AchievementsComponent } from './page/achievements/achievements.component';
import { GoalsGridOrderPipe } from './pipes/goals-grid-order.pipe';

@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    GoalsComponent,
    NavigationComponent,
    CreateGoalComponent,
    AchievementsComponent,
    GoalsGridOrderPipe,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatChipsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
