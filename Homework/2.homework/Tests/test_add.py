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

        baza.send("add")
        baza.expect("add> EMSO:")
        baza.send("310594050023")
        baza.expect("add> NAME:")
        baza.send("Janez Albertt")
        baza.expect("add> SURNAME:")
        baza.send("Novakk")
        baza.expect("add> AGE:")
        baza.send("1")
        baza.expect(">> Invalid input data")
        baza.expect("command>")

        baza.send("add")
        baza.expect("add> EMSO:")
        baza.send("3105940500235")
        baza.expect("add> NAME:")
        baza.send("Janez Albertt")
        baza.expect("add> SURNAME:")
        baza.send("Novakk")
        baza.expect("add> AGE:")
        baza.send("-1")
        baza.expect(">> Invalid input data")
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
        baza.expect(">> Patient already exists")
        baza.expect("command>")

        baza.send("exit")
        baza.expect("Bye")

        print "PASSED\ttest_add"

    except:
        print "FAILED\ttest_add"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_bst_print()

