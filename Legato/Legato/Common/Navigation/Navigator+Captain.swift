//
//  NavigationHelper.swift
//  YellowPod
//
//  Created by Erick A. Montañez  on 11/29/17.
//  Copyright © 2017 YellowPod. All rights reserved.
//

import Foundation

//MARK: Nodes
public enum Segue {
    //Here add your segues
    // NO SEGUES FOR THIS PROJECT
}

public enum Storyboard: String {
    //Here add your storyboard identifiers
    case main = "Main"
    case login = "Login"
    case error = "Error"
    case dispatch = "Dispatch"
}

public enum Controller: String {
    //Here add your view controllers by name
    case login = "LoginViewController"
    case dispatch = "DispatchViewController"
    case error = "ErrorViewController"
    case main = "MainViewController"
}

