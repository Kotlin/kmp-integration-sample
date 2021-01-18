//
//  SimpleLoginIOSApp.swift
//  SimpleLoginIOS
//
//  Created by Ekaterina.Petrova on 15.01.2021.
//

import SwiftUI
import shared

@main
struct SimpleLoginIOSApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView(viewModel: .init(loginRepository: LoginRepository(dataSource: LoginDataSource()),
                                         loginValidator: LoginDataValidator()))
        }
    }
}
