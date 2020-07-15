import pexpect2
 

def test_bst_print():
    baza = pexpect2.pexpect()

    try:
        baza.expect("Enter command: ")

        baza.send("use bst")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add Andrej Novak 15 2111935500138")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add Janez Levak 15 2111935500132")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("print")
        baza.expect("2111935500138 | Novak, Andrej | 15")
        baza.expect("\t2111935500132 | Levak, Janez | 15")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("count")
        baza.expect("2")
        baza.expect("Enter command: ")

        baza.send("depth")
        baza.expect("2")
        baza.expect("Enter command: ")

        print "PASSED\ttest_bst_print"

    except:
        print "FAILED\ttest_bst_print"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_bst_print()

