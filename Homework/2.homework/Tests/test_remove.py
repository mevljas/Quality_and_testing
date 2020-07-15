import pexpect
 

def test_bst_print():
    baza = pexpect.pexpect()

    try:
        baza.expect("command>")

        baza.send("add")
        baza.expect("add> EMSO:")
        baza.send("3105940500232")
        baza.expect("add> NAME:")
        baza.send("Janez Albert")
        baza.expect("add> SURNAME:")
        baza.send("Novak")
        baza.expect("add> AGE:")
        baza.send("80")
        baza.expect(">> OK")
        baza.expect("command>")

        baza.send("remove 3105940500232")
        baza.expect(">> OK")
        baza.expect("command>")

        baza.send("add")
        baza.expect("add> EMSO:")
        baza.send("3105940500232")
        baza.expect("add> NAME:")
        baza.send("Janez Albert")
        baza.expect("add> SURNAME:")
        baza.send("Novak")
        baza.expect("add> AGE:")
        baza.send("80")
        baza.expect(">> OK")
        baza.expect("command>")

        baza.send("remove")
        baza.expect("remove> NAME:")
        baza.send("Janez Albert")
        baza.expect("remove> SURNAME:")
        baza.send("Novak")
        baza.expect(">> OK")
        baza.expect("command>")

        baza.send("add")
        baza.expect("add> EMSO:")
        baza.send("3105940500232")
        baza.expect("add> NAME:")
        baza.send("Janez Albert")
        baza.expect("add> SURNAME:")
        baza.send("Novak")
        baza.expect("add> AGE:")
        baza.send("80")
        baza.expect(">> OK")
        baza.expect("command>")

        baza.send("remove 31059405002322")
        baza.expect(">> Invalid input data")
        baza.expect("command>")

        baza.send("remove 3105940500238")
        baza.expect(">> Patient does not exist")
        baza.expect("command>")


        baza.send("exit")
        baza.expect("Bye")

        print "PASSED\ttest_remove"

    except:
        print "FAILED\ttest_remove"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_bst_print()

