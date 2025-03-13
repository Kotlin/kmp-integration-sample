import SwiftUI
import sharedKit

@main
struct SimpleLoginIOSApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView(viewModel: .init(loginRepository: LoginRepository(dataSource: LoginDataSource()), loginValidator: LoginDataValidator()))
        }
    }
}
