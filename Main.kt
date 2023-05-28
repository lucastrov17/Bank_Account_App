fun main() {
    println(
        "Welcome to your banking system. \n" +
                "What type of account would you like to create?\n" +
                "1. Debit account\n" +
                "2. Credit account\n" +
                "3. Checking account"
    )

    var accountType: String = ""
    var userChoice: Int = 0

    while (accountType == "") {
        println("Choose an option (1, 2 or 3)")
        userChoice += (1..5).random()
        println("The selected option is $userChoice.")

        when (userChoice) {
            1 -> accountType += "debit"
            2 -> accountType += "credit"
            3 -> accountType += "checking"
            else -> continue
        }
        println("You have created a $accountType account.")
    }
    var accountBalance: Int = 0
    accountBalance += (0..1000).random()
    println("The current balance is $accountBalance dollars.")

    val money: Int = (0..1000).random()
    println("The amount you transferred is $money dollars.")

    var output: Int = 0

    fun withdraw(amount: Int): Int {
        accountBalance -= amount
        println("You successfully withdrew $amount dollars. The current balance is $accountBalance dollars.")
        return amount
    }
    output = withdraw(money)
    println("The amount you withdrew is $output dollars.")

    fun debitWithdraw(amount: Int): Int {
        if (accountBalance == 0) {
            println("Can't withdraw, no money on this account!")
            return accountBalance
        } else if (amount > accountBalance) {
            println("Not enough money on this account! The current balance is $accountBalance dollars.")
            return 0
        } else {
            return withdraw(amount)
        }
    }
    output = debitWithdraw(money)
    println("The amount you withdrew is $output dollars.")

    fun deposit(amount:Int): Int {
        accountBalance += amount
        println("You successfully deposited $amount dollars. The current balance is $accountBalance dollars.")
        return amount
    }
    output = deposit(money)
    println("The amount you deposited is $output dollars.")

    fun creditDeposit(amount:Int): Int {
        if (accountBalance == 0) {
            println("You don’t need to deposit anything in order to pay off the account since it has already been paid off. ")
            return accountBalance
        } else if (amount+accountBalance > 0) {
            println("Deposit failed, you tried to pay off an amount greater than the credit balance. The checking balance is $accountBalance dollars.")
            return 0
        } else if (amount == -accountBalance){
            accountBalance = 0
            println("You have paid off this account!")
            return amount
        } else {
            return deposit(amount)
        }
    }
    output = creditDeposit(money)
    println("The amount you deposited is $output dollars.")

    fun transfer(mode:String) {
        var transferAmount: Int

        when (mode) {
            "withdraw" -> {
                if (accountType == "debit") {
                    transferAmount = debitWithdraw(money)
                } else {
                    transferAmount = withdraw(money)
                }
                println("The amount you withdrew is $transferAmount dollars.")
            }
            "deposit" -> {
                if (accountType == "credit") {
                    transferAmount = creditDeposit(money)
                } else {
                    transferAmount = deposit(money)
                }
                println("The amount you deposited is $transferAmount dollars.")
            }
            else -> return
        }
    }

    var isSystemOpen:Boolean = true
    var option:Int = 0

    while (isSystemOpen == true) {
        println(
            "What would you like to do?\n" +
                    "\n" +
                    "1. Check bank account balance\n" +
                    "\n" +
                    "2. Withdraw money\n" +
                    "\n" +
                    "3. Deposit money\n" +
                    "\n" +
                    "4. Close the app\n" +
                    "\n" +
                    "Which option do you choose? (1, 2, 3 or 4)")

        option = (1..5).random()
        println("You have selected the $option option.")

        when (option) {
            1 -> println("The current balance is $accountBalance dollars.")
            2 -> transfer("withdraw")
            3 -> transfer("deposit")
            4 -> {
                isSystemOpen = false
                println ("Application Closed")
            }
            else -> continue
        }

    }
}
