//
//  DataFaker.swift
//  Legato
//
//  Created by Luis Burgos on 7/23/18.
//  Copyright © 2018 Yellowme. All rights reserved.
//

import Foundation

public class DataProvider { }

/// 1st validation
class SessionFaker: DataProvider {
    func hasSessionStarted() -> Bool {
        return true
    }
    
}

/// 2nd validation
class ValidSessionFaker: DataProvider {
    func hasValidSession() -> Bool {
        return true
    }
}

/// 3rd validation
class UserFaker: DataProvider {
    func userHasSomeValidProperty() -> Bool {
        return true
    }
}
