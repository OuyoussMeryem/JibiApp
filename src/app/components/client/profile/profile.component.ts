import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  client: any;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    // this.client = this.authService.getCurrentUser();
  }

}
