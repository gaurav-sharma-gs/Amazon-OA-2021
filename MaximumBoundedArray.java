//https://aonecode.com/interview-question/maximum-bounded-array

public static int[] maximumBoundedArray(int n, int lowBound, int upBound){
        int result[] = new int[n];
        int diff = upBound-lowBound;
        if(diff < n/2)
            return result;

        int mid = 0;
        if(n%2 == 0)
            mid = n/2 -1;
        else
            mid = n/2;

        result[mid] = upBound;
        for(int i = mid-1;i>=0;i--){
            result[i] = upBound - mid + i;
        }

        for(int i = mid; i < n; i++){
            result[i] = upBound - i + mid;
        }
        return result;
    }
