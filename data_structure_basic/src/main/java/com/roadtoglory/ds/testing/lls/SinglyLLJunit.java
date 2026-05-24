/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing.lls;
/*
*
*

This class SinglyLLJunit is created and managed by subhe
Created on 24-05-2026 at 00:57 for the project Linked List Testing

*
*
*/

import com.roadtoglory.ds.linkedlists.SinglyLL;
import org.junit.Test;

public class SinglyLLJunit {

    @Test
    public void testcase1() {
        SinglyLL singlyLL = new SinglyLL(10);
        singlyLL.append(20);
        singlyLL.append(30);
        singlyLL.append(40);
        singlyLL.append(50);
        singlyLL.append(60);
        singlyLL.append(70);
        singlyLL.append(80);
        singlyLL.append(90);
        singlyLL.printList();
        singlyLL.insert(65, 6);
        singlyLL.prepend(5);
        singlyLL.printList();

        System.out.println("removed " + singlyLL.removeIndexWise(7));
        singlyLL.printList();
    }
}
