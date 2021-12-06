package cs.vsu.ru.kapustin;

public class CheckingForSequence {

    public boolean checkArray(int[][] arr) {
        int maxLine = arr.length - 1, maxColumn = arr[0].length - 1;
        int sequenceType = 0;

        if (maxColumn > 0) {
            sequenceType = Integer.compare(arr[0][1], arr[0][0]);
        } else if (maxLine > 0 && maxColumn == 0) {
            sequenceType = Integer.compare(arr[1][0], arr[0][0]);
        }

        if (maxLine == 0) {
            return checkOneDimensionalArray(sequenceType, arr, maxColumn, true);
        } else if (maxColumn == 0) {
            return checkOneDimensionalArray(sequenceType, arr, maxLine, false);
        }

        return checkTwoDimensionalArray(arr, sequenceType, maxColumn, maxLine);
    }

    private boolean checkOneDimensionalArray(int sequenceType, int[][] arr, int maxIndex, boolean isHorizontalArray) {
        int newPossibleMemberOfSeq, lastMemberOfSeq = arr[0][0];

        for (int i = 1; i <= maxIndex; i++) {
            if (isHorizontalArray) {
                newPossibleMemberOfSeq = arr[0][i];
            } else {
                newPossibleMemberOfSeq = arr[i][0];
            }

            if (isNumberMemberOfSequence(newPossibleMemberOfSeq, lastMemberOfSeq, sequenceType)) {
                lastMemberOfSeq = newPossibleMemberOfSeq;
            } else {
                return false;
            }
        }

        return true;
    }

    private boolean checkTwoDimensionalArray(int[][] arr, int sequenceType, int maxColumn, int maxLine) {
        int sequenceLength = 1;
        int lineIndex = 0, columnIndex = 0;
        int newPossibleMemberOfSeq, lastMemberOfSeq = arr[0][0];
        boolean shouldShiftDown = true, shouldShiftRight = false;

        while (sequenceLength != (maxColumn + 1) * (maxLine + 1)) {
            if (!shouldShiftDown && !shouldShiftRight) {
                if (lineIndex == 0 || columnIndex == maxColumn) {

                    while (columnIndex > 0 && lineIndex < maxLine) {
                        lineIndex++;
                        columnIndex--;
                        newPossibleMemberOfSeq = arr[lineIndex][columnIndex];

                        if (isNumberMemberOfSequence(newPossibleMemberOfSeq, lastMemberOfSeq, sequenceType)) {
                            lastMemberOfSeq = newPossibleMemberOfSeq;
                            sequenceLength++;
                        } else {
                            return false;
                        }
                    }

                    if (columnIndex == 0 && lineIndex != maxLine) {
                        shouldShiftDown = true;
                    } else if (lineIndex == maxLine && columnIndex != maxColumn) {
                        shouldShiftRight = true;
                    }
                } else {

                    while (lineIndex > 0 && columnIndex < maxColumn) {
                        lineIndex--;
                        columnIndex++;
                        newPossibleMemberOfSeq = arr[lineIndex][columnIndex];

                        if (isNumberMemberOfSequence(newPossibleMemberOfSeq, lastMemberOfSeq, sequenceType)) {
                            lastMemberOfSeq = newPossibleMemberOfSeq;
                            sequenceLength++;
                        } else {
                            return false;
                        }

                        if (lineIndex == 0 && columnIndex != maxColumn) {
                            shouldShiftRight = true;
                        } else if (columnIndex == maxColumn && lineIndex != maxLine) {
                            shouldShiftDown = true;
                        }
                    }
                }
            } else {
                if (shouldShiftDown) {
                    lineIndex += 1;
                    shouldShiftDown = false;
                } else {
                    columnIndex += 1;
                    shouldShiftRight = false;
                }
                newPossibleMemberOfSeq = arr[lineIndex][columnIndex];

                if (isNumberMemberOfSequence(newPossibleMemberOfSeq, lastMemberOfSeq, sequenceType)) {
                    lastMemberOfSeq = newPossibleMemberOfSeq;
                    sequenceLength++;
                } else {
                    return false;
                }
            }

        }

        return true;
    }

    private boolean isNumberMemberOfSequence(int newPossibleMemberOfSeq, int lastMemberOfSeq, int sequenceType) {
        if (sequenceType == 1) {
            return newPossibleMemberOfSeq > lastMemberOfSeq;
        } else if (sequenceType == 0) {
            return newPossibleMemberOfSeq == lastMemberOfSeq;
        } else {
            return newPossibleMemberOfSeq < lastMemberOfSeq;
        }
    }

}
