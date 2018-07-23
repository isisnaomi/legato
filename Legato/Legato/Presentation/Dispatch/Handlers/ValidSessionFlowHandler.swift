//
//  ValidSessionFlowHandler.swift
//  Legato
//
//  Created by Luis Burgos on 7/23/18.
//  Copyright © 2018 Yellowme. All rights reserved.
//

import Foundation

class ValidSessionFlowHandler: FlowHandler<DataProvider, Flows> {
    override func runValidations(_ callback: FlowHandlerCallback) {
        if (logicHandler as! ValidSessionFaker).hasValidSession() {
            executeNext(callback)
        } else {
            executeEarlyExit(callback)
        }
    }
}
