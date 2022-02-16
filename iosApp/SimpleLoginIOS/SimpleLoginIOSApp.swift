//
//  SimpleLoginIOSApp.swift
//  SimpleLoginIOS
//
//  Created by Danil.Pavlov on 16.02.2022.
//

import SwiftUI
import shared
 
@main
struct SimpleLoginIOSApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView(viewModel: .init(loginRepository: LoginRepository(dataSource: LoginDataSource()), loginValidator: LoginDataValidator()))
        }
    }
}
