//
//  DispatchPresenter.swift
//  Legato
//
//  Created by Luis Burgos on 7/23/18.
//  Copyright © 2018 Yellowme. All rights reserved.
//

import Foundation

class DispatchPresenter: DispatchViewPresenter {
    
    private var flowHandler: FlowHandler<DataProvider, Flows>
    private var currentFlow: Flows = .main
    
    let view: DispatchView!
    
    required init(view: DispatchView) {
        self.view = view
        flowHandler = SessionFlowHandler(
            SessionFaker(), concreteFlow: .login
        )
        
        _ = flowHandler
            .link(with: ValidSessionFlowHandler(
                ValidSessionFaker(), concreteFlow: .login
            ))
            .link(with: FetchUserFlowHandler(
                UserFaker(), concreteFlow: .error
            ))
    }
    
    func decideUserMainScreen() {
        view.setProgress(active: true)
        flowHandler.runValidations(self)
    }
    
    func sendToMainScreen() {
        switch currentFlow {
        case .login:
            view.sendToLogin()
        case .error:
            view.sendToError()
        case .main:
            view.sendToMain()
        }
    }
}

extension DispatchPresenter: FlowHandlerCallback {
    func onEndReached() {
        executeAfterDecideMainScreenAction()
    }
    
    func onEarlyExit(flow: Any) {        
        currentFlow = flow as! Flows
        executeAfterDecideMainScreenAction();
    }
}

extension DispatchPresenter {
    private func executeAfterDecideMainScreenAction() {
        DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
            self.view.setProgress(active: false)
            self.sendToMainScreen()
        }
    }
}
